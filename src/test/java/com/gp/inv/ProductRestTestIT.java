package com.gp.inv;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.hamcrest.Matchers;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-it.properties")
// Not particularly clean to dictate test order, but for integration tests I deem it acceptable.
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductRestTestIT {

    @Autowired
    private MockMvc mvc;

    private RequestPostProcessor merchantHandelaar1() {
        return httpBasic("handelaar1", "correctbatteryhorsestaple");
    }

    private String asJSON(Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }

    @Test
    public void t1_getAllProductsForUser() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/products")
            .with(merchantHandelaar1())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(content()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].name", is("sokken")))
            // Test dataset has 2 items for this user
            .andExpect(jsonPath("$.length()", Matchers.greaterThan(1)));
    }

    @Test
    public void t2_addAndDeleteProduct() throws Exception {
        Product product = new Product();
        product.setName("Rugtas");

        mvc.perform(MockMvcRequestBuilders.put("/products")
            .with(merchantHandelaar1())
            .content(asJSON(product))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.redirectedUrlPattern("**/products/**"))
            .andExpect(content()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.name", is(product.getName())))
            .andExpect(jsonPath("$.id", greaterThan(0)))

            .andDo( result -> {
                String resourceUrl = result.getResponse().getRedirectedUrl();
                mvc.perform(MockMvcRequestBuilders.delete(resourceUrl)
                    .with(merchantHandelaar1())
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isNoContent());
            });
    }

    @Test
    public void t3_deleteNonExisting() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/products/812371")
            .with(merchantHandelaar1())
            .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void t4_delete() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/products/1")
            .with(merchantHandelaar1())
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
