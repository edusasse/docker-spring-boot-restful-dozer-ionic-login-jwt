package com.edusasse.app.security.csrf;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CsrfDisabledIntegrationTest extends CsrfAbstractIntegrationTest {

	// TODO: fix test
	@Ignore
    @Test
    public void givenNoAuthWhenAddParametroThenUnauthorized() throws Exception {
        mvc.perform(post("/auth/parametros").contentType(MediaType.APPLICATION_JSON).content(createParametroFirst())).andExpect(status().isUnauthorized());
    }

	// TODO: fix test
	@Ignore
    @Test
    public void givenAuthWhenAddParametroThenCreated() throws Exception {
        mvc.perform(post("/auth/parametros").contentType(MediaType.APPLICATION_JSON).content(createParametroFirst()).with(testUser())).andExpect(status().isCreated());
    }
  
}
