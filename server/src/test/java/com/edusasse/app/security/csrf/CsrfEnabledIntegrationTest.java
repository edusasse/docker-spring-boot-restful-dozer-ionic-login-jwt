package com.edusasse.app.security.csrf;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import com.edusasse.app.config.WebConfig;
import com.edusasse.app.config.security.SecurityConfig;


@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration(classes = { SecurityConfig.class, WebConfig.class })
public class CsrfEnabledIntegrationTest extends CsrfAbstractIntegrationTest {

    @Test
    public void givenNoCsrfWhenAddParametroThenForbidden() throws Exception {
        mvc.perform(post("/auth/parametros").contentType(MediaType.APPLICATION_JSON).content(createParametroFirst()).with(testUser())).andExpect(status().isForbidden());
    }

    @Test
    public void givenCsrfWhenAddParametroThenCreated() throws Exception {
        mvc.perform(post("/auth/parametros").contentType(MediaType.APPLICATION_JSON).content(createParametroSecond()).with(testAdmin()).with(csrf())).andExpect(status().isCreated());
    }

}
