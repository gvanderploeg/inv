package com.gp.inv;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

/**
 * Integration tests for the whole application.
 * Will be run by Maven Failsafe plugin (not surefire) upon activating maven profile 'it'.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = Application.class)
@AutoConfigureMockMvc
public class AuthenticationTestIT {

    @Autowired
    private MockMvc mvc;

    @Test
    public void unauthenticatedFails() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/products")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().is4xxClientError())
            .andExpect(MockMvcResultMatchers.header().exists("WWW-Authenticate"));
    }

    @Test
    public void authenticatedSucceeds() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/products")
            .with(httpBasic("handelaar1", "correctbatteryhorsestaple"))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void authenticationWithNonExistingUser() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/products")
            .with(httpBasic("nonexistinguser", "password123"))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
}
