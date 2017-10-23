package com.edusasse.app.config;

import static org.dozer.loader.api.FieldsMappingOptions.customConverter;

import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.edusasse.app.dto.CidadeDTO;
import com.edusasse.app.dto.ClienteDTO;
import com.edusasse.app.dto.EnderecoDTO;
import com.edusasse.app.dto.FeriadoDTO;
import com.edusasse.app.dto.ParametroDTO;
import com.edusasse.app.dto.PessoaFisicaDTO;
import com.edusasse.app.dto.TelefoneDTO;
import com.edusasse.app.dto.UnidadeFederacaoDTO;
import com.edusasse.app.dto.UsuarioDTO;
import com.edusasse.app.dto.converters.CidadeConverter;
import com.edusasse.app.dto.converters.PessoaConverter;
import com.edusasse.app.dto.converters.PessoaFisicaConverter;
import com.edusasse.app.dto.converters.SexoConverter;
import com.edusasse.app.dto.converters.SituacaoClienteConverter;
import com.edusasse.app.dto.converters.TipoDocumentoConverter;
import com.edusasse.app.dto.converters.TipoEnderecoConverter;
import com.edusasse.app.dto.converters.TipoTelefoneConverter;
import com.edusasse.app.dto.converters.UnidadeFederacaoConverter;
import com.edusasse.app.entity.Cidade;
import com.edusasse.app.entity.Cliente;
import com.edusasse.app.entity.Endereco;
import com.edusasse.app.entity.Feriado;
import com.edusasse.app.entity.Parametro;
import com.edusasse.app.entity.PessoaFisica;
import com.edusasse.app.entity.Telefone;
import com.edusasse.app.entity.UnidadeFederacao;
import com.edusasse.app.entity.Usuario;

@Configuration
public class MappingConfig {

    @Bean
    public BeanMappingBuilder beanMappingBuilder() {
        return new BeanMappingBuilder() {
            @Override
            protected void configure() {
            	
             
            	// Cidade
                mapping(Cidade.class, CidadeDTO.class)
                .fields(field("idCidade").accessible(), field("id").accessible())
                .fields("nmCidade", "nome")
                .fields("unidadeFederacao", "uf", customConverter(UnidadeFederacaoConverter.class))
                .fields("dhUltimaAlteracao", "dateUltimaAlteracao")
                .fields("dhCadastro", "dateCadastro")
                .fields("nrVersao", "versao");
                
                // Cliente
                mapping(Cliente.class, ClienteDTO.class)
                .fields(field("id").accessible(), field("id").accessible())
                .fields("cdSituacao", "situacao", customConverter(SituacaoClienteConverter.class))
                .fields("dsObservacao", "observacao")
                .fields("flVisto", "visto")
                .fields("pessoa", "pessoa", customConverter(PessoaConverter.class))
                .fields("dhUltimaAlteracao", "dateUltimaAlteracao")
                .fields("dhCadastro", "dateCadastro")
                .fields("nrVersao", "versao");
                
                // Endereco
                mapping(Endereco.class, EnderecoDTO.class)
                .fields(field("idEndereco").accessible(), field("id").accessible())
                .fields("dsBairro", "bairro")
                .fields("dsComplemento", "complemento")
                .fields("dsLogradouro", "logradouro")
                .fields("nrLogradouro", "numeroLogradouro")
                .fields("dsCep", "cep")
                .fields("flTipoEndereco", "tipoEndereco", customConverter(TipoEnderecoConverter.class))
                .fields("cidade", "cidade", customConverter(CidadeConverter.class))
                .fields("pessoa", "pessoa", customConverter(PessoaConverter.class))
                .fields("dhUltimaAlteracao", "dateUltimaAlteracao")
                .fields("dhCadastro", "dateCadastro")
                .fields("nrVersao", "versao");
                
                // Feriado
                mapping(Feriado.class, FeriadoDTO.class)
                .fields(field("id").accessible(), field("id").accessible())
                .fields("dtFeriado", "dateFeriado")
                .fields("dhUltimaAlteracao", "dateUltimaAlteracao")
                .fields("dhCadastro", "dateCadastro")
                .fields("nrVersao", "versao");
                
                // Parametro
                mapping(Parametro.class, ParametroDTO.class)
                .fields(field("id").accessible(), field("id").accessible())
                .fields("dsParametro", "parametro")
                .fields("vlParametro", "valor")
                .fields("dhUltimaAlteracao", "dateUltimaAlteracao")
                .fields("dhCadastro", "dateCadastro")
                .fields("nrVersao", "versao");
                
            	// PessoaFisica
                mapping(PessoaFisica.class, PessoaFisicaDTO.class)
                .fields(field("idPessoa").accessible(), field("id").accessible())
                .fields("nmPrimeiroNome", "identity.primeiroNome")
                .fields("nmSegundoNome", "identity.segundoNome")
                .fields("cdSexo", "identity.sexo", customConverter(SexoConverter.class))
                .fields("dsEmail", "eMail")
                .fields("dtNascimento", "dataNascimento")
                .fields("dsObservacao", "observacao")
                .fields("dsDocumento", "documento.documento")
                .fields("dsTipoDocumento", "documento.tipoDocumento", customConverter(TipoDocumentoConverter.class))
                .fields("dhUltimaAlteracao", "dateUltimaAlteracao")
                .fields("dhCadastro", "dateCadastro")
                .fields("nrVersao", "versao")
                .exclude("dsEmailFaturamento");
                
                // Telefone	
                mapping(Telefone.class, TelefoneDTO.class)
                .fields(field("idTelefone").accessible(), field("id").accessible())
                .fields("nrTelefone", "numeroTelefone")
                .fields("nmPessoaContato", "nomeContato")
                .fields("dsTipoTelefone", "tipoTelefone", customConverter(TipoTelefoneConverter.class))
                .fields("pessoa", "pessoa", customConverter(PessoaConverter.class))
                .fields("dhUltimaAlteracao", "dateUltimaAlteracao")
                .fields("dhCadastro", "dateCadastro")
                .fields("nrVersao", "versao");
                
                // Unidade Federacao
                mapping(UnidadeFederacao.class, UnidadeFederacaoDTO.class)
                .fields(field("idUf").accessible(), field("id").accessible())
                .fields("dsUf", "uf")
                .fields("dhUltimaAlteracao", "dateUltimaAlteracao")
                .fields("dhCadastro", "dateCadastro")
                .fields("nrVersao", "versao");
                
                // Usuario
                mapping(Usuario.class, UsuarioDTO.class)
                .fields(field("id").accessible(), field("id").accessible())
                .fields("dsApelido", "apelido")
                .fields("dsSenha", "senha")
                .fields("flAtivo", "ativo")
                .fields("dsPerfil", "perfil")
                .fields("pessoaFisica", "pessoa", customConverter(PessoaFisicaConverter.class))
                .fields("dhUltimaAlteracao", "dateUltimaAlteracao")
                .fields("dhCadastro", "dateCadastro")
                .fields("nrVersao", "versao");
                
            }
        };
    }

    @Bean
    public DozerBeanMapper beanMapper() {
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        dozerBeanMapper.addMapping(beanMappingBuilder());
        return dozerBeanMapper;
    }
}
