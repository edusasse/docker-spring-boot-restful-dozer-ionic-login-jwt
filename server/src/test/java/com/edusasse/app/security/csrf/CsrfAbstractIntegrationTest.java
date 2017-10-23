package com.edusasse.app.security.csrf;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import java.util.Date;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.edusasse.app.dto.ParametroDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.NONE)
public abstract class CsrfAbstractIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private Filter springSecurityFilterChain;

    protected MockMvc mvc;

    //

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).addFilters(springSecurityFilterChain).build();
    }

    protected RequestPostProcessor testUser() {
        return user("user").password("user").roles("USER");
    }

    protected RequestPostProcessor testAdmin() {
        return user("admin").password("admin").roles("ADMIN");
    }

    protected String createParametroFirst() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(new ParametroDTO((long) 98, "Test", "Test", new Date(), null, 0));
    }
    
    protected String createParametroSecond() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(new ParametroDTO((long) 99, "Test", "Test", new Date(), null, 0));
    }
}
