package ro.disi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ro.disi.DISIApplicationTestConfig;
import ro.disi.dtos.PersonDummyDetailsDTO;
import ro.disi.services.PersonDummyService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class PersonDummyControllerUnitTest extends DISIApplicationTestConfig {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonDummyService service;

    @Test
    public void insertPersonTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        PersonDummyDetailsDTO personDummyDTO = new PersonDummyDetailsDTO("John", "Somewhere Else street", 22);

        mockMvc.perform(post("/personDummy")
                .content(objectMapper.writeValueAsString(personDummyDTO))
                .contentType("application/json"))
                .andExpect(status().isCreated());
    }

    @Test
    public void insertPersonTestFailsDueToAge() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        PersonDummyDetailsDTO personDummyDTO = new PersonDummyDetailsDTO("John", "Somewhere Else street", 17);

        mockMvc.perform(post("/personDummy")
                .content(objectMapper.writeValueAsString(personDummyDTO))
                .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void insertPersonTestFailsDueToNull() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        PersonDummyDetailsDTO personDummyDTO = new PersonDummyDetailsDTO("John", null, 17);

        mockMvc.perform(post("/personDummy")
                .content(objectMapper.writeValueAsString(personDummyDTO))
                .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }
}
