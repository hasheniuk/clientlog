package org.clientlog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.clientlog.App;
import org.clientlog.dto.ClientDTO;
import org.clientlog.dto.ClientRequest;
import org.clientlog.search.specification.ClientSearchSpecification;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(locations = "classpath:test.properties")
public class ClientRestControllerTest {

    private static ClientDTO RALPH = new ClientDTO();
    private static ClientDTO HOMER_CLONE = new ClientDTO();
    private static ClientDTO UPDATE_MAGGIE = new ClientDTO();

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeClass
    public static void classSetup() {
        RALPH.setFirstName("Ralph");
        RALPH.setLastName("Ralph Wiggum");
        RALPH.setEmail("ralph.wiggum@springfield.org");

        HOMER_CLONE.setFirstName("Homer Clone");
        HOMER_CLONE.setLastName("Simpson");
        HOMER_CLONE.setEmail("homer.simpson@springfield.org");

        UPDATE_MAGGIE.setId(5L);
        UPDATE_MAGGIE.setFirstName("Maggie");
        UPDATE_MAGGIE.setLastName("Simpson");
        UPDATE_MAGGIE.setEmail("maggie.simpson@springfield.org");
        UPDATE_MAGGIE.setPhone("1111111");
    }

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void test_find_all() throws Exception {
        mockMvc.perform(get("/client"))
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    public void test_find_page_2_count_2() throws Exception {
        mockMvc.perform(get("/client?page=2&count=2"))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void test_find_serch_request() throws Exception {
        mockMvc.perform(get("/client?firstName=homer&email=marge&anyMatch=true"))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void test_find() throws Exception {
        mockMvc.perform(get("/client/1"))
                .andExpect(jsonPath("$.firstName", is("Homer")));
    }

    @Test
    public void test_find_not_existed() throws Exception {
        mockMvc.perform(get("/client/6"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void test_create() throws Exception {
        mockMvc.perform(post("/client")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(RALPH)))
                .andExpect(status().isOk());
    }

    @Test
    public void test_create_existing_email() throws Exception {
        mockMvc.perform(post("/client")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(HOMER_CLONE)))
                .andExpect(status().isConflict());
    }

    @Test
    public void test_update() throws Exception {
        mockMvc.perform(put("/client/5")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(UPDATE_MAGGIE)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.phone", is("1111111")));
    }

    @Test
    public void test_delete() throws Exception {
        mockMvc.perform(delete("/client/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void test_delete_not_existed() throws Exception {
        mockMvc.perform(delete("/client/6"))
                .andExpect(status().isNotFound());
    }
}
