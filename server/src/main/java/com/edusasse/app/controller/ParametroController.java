package com.edusasse.app.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.edusasse.app.dto.ParametroDTO;
import com.edusasse.app.entity.Parametro;
import com.edusasse.app.facade.IParametroFacade;
import com.edusasse.app.facade.PageDTO;
import com.edusasse.app.persistence.dao.repository.specification.GenericSpecification;
import com.edusasse.app.persistence.dao.repository.specification.GenericSpecificationsBuilder;
import com.edusasse.app.persistence.dao.rsql.CustomRsqlVisitor;
import com.edusasse.app.web.exception.MyResourceNotFoundException;
import com.edusasse.app.web.hateoas.event.PaginatedResultsRetrievedEvent;
import com.edusasse.app.web.hateoas.event.ResourceCreatedEvent;
import com.edusasse.app.web.hateoas.event.SingleResourceRetrievedEvent;
import com.edusasse.app.web.util.CriteriaParser;
import com.edusasse.app.web.util.RestPreconditions;
import com.edusasse.app.web.util.SearchOperation;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;

@RestController
@RequestMapping(value = "/auth/parametros")
public class ParametroController {

	@Autowired
    private ApplicationEventPublisher eventPublisher;
	
    @Autowired
    private IParametroFacade facade;
     
    public ParametroController() {
        super();
    }

    // API - READ

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ParametroDTO findById(@PathVariable("id") final Long id, final HttpServletResponse response) {
        final ParametroDTO resourceById = RestPreconditions.checkFound(facade.findOne(id));
        eventPublisher.publishEvent(new SingleResourceRetrievedEvent(this, response));
        
        return resourceById;
    }
    
    @RequestMapping(params = { "page", "size" }, method = RequestMethod.GET)
    @ResponseBody
    public List<ParametroDTO> findPaginated(@RequestParam("page") final int page, @RequestParam("size") final int size, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        final PageDTO<ParametroDTO> resultPage = facade.findPaginated(page, size);
        if (page > resultPage.getTotalPages()) {
            throw new MyResourceNotFoundException();
        }
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<Parametro>(Parametro.class, uriBuilder, response, page, resultPage.getTotalPages(), size));

        return resultPage.getContent();
    }
    
    @GetMapping("/pageable")
    @ResponseBody
    public List<ParametroDTO> findPaginatedWithPageable(Pageable pageable, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        final PageDTO<ParametroDTO> resultPage = facade.findPaginated(pageable);
        if (pageable.getPageNumber() > resultPage.getTotalPages()) {
            throw new MyResourceNotFoundException();
        }
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<ParametroDTO>(ParametroDTO.class, uriBuilder, response, pageable.getPageNumber(), resultPage.getTotalPages(), pageable.getPageSize()));

        return resultPage.getContent();
    }
    
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<ParametroDTO> findAll(@RequestParam(value = "filter", required = false) String filter) {
        return facade.findAllByCriteria(filter);
    }

    @GetMapping(value = "/search")
    @ResponseBody
    public List<ParametroDTO> findAllByOrPredicate(@RequestParam(value = "filter") String filter) {
        final Specification<Parametro> spec = resolveSpecification(filter);
        return facade.findAllBySpecification(spec);
    }

    @GetMapping(value = "/searchQuery")
    @ResponseBody
    public List<ParametroDTO> findAllByAdvPredicate(@RequestParam(value = "query") String query) {
        final Specification<Parametro> spec = resolveSpecificationFromInfixExpr(query);
        return facade.findAllBySpecification(spec);
    }

    protected Specification<Parametro> resolveSpecificationFromInfixExpr(String searchParameters) {
        CriteriaParser parser = new CriteriaParser();
        GenericSpecificationsBuilder<Parametro> specBuilder = new GenericSpecificationsBuilder<>();
        return specBuilder.build(parser.parse(searchParameters), GenericSpecification<Parametro>::new);
    }

    protected Specification<Parametro> resolveSpecification(String searchParameters) {
        GenericSpecificationsBuilder<Parametro> builder = new GenericSpecificationsBuilder<Parametro>();
        String operationSetExper = Joiner.on("|")
            .join(SearchOperation.SIMPLE_OPERATION_SET);
        Pattern pattern = Pattern.compile("(\\p{Punct}?)(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
        Matcher matcher = pattern.matcher(searchParameters + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(5), matcher.group(4), matcher.group(6));
        }
        return builder.build();
    }
 
    @RequestMapping(method = RequestMethod.GET, value = "/rsql")
    @ResponseBody
    public List<ParametroDTO> findAllByRsql(@RequestParam(value = "search") String search) {
        Node rootNode = new RSQLParser().parse(search);
        Specification<Parametro> spec = rootNode.accept(new CustomRsqlVisitor<Parametro>());
        return facade.findAllBySpecification(spec);
    }

   // API - WRITE

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ParametroDTO create(@RequestBody final ParametroDTO resource, final HttpServletResponse response) {
        Preconditions.checkNotNull(resource);
        final ParametroDTO parametro = facade.create(resource);
        final Long idOfCreatedResource = parametro.getId();
        eventPublisher.publishEvent(new ResourceCreatedEvent(this, response, idOfCreatedResource));

        return parametro;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") final Long id, @RequestBody final ParametroDTO resource) {
        Preconditions.checkNotNull(resource);
        RestPreconditions.checkFound(facade.findOne(resource.getId()));
        facade.update(resource);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") final Long id) {
        facade.deleteById(id);
    }

    @RequestMapping(method = RequestMethod.HEAD)
    @ResponseStatus(HttpStatus.OK)
    public void head(final HttpServletResponse resp) {
        resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }
    
}