package com.luca.AutogarageGianlucaMeens.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luca.AutogarageGianlucaMeens.Part.Part;
import com.luca.AutogarageGianlucaMeens.Part.PartController;
import com.luca.AutogarageGianlucaMeens.Part.PartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PartController.class)
public class PartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PartRepository partRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldGetAllParts() throws  Exception{
        long id1 = 1;
        long id2 = 2;
        List<com.luca.AutogarageGianlucaMeens.Part.Part> parts = new ArrayList<>(
                Arrays.asList(  new com.luca.AutogarageGianlucaMeens.Part.Part(id1, "spiegel", 20.00, 200.00, 2),
                                new Part(id2, "zuiger", 200.00, 300.00, 6)));
        when(partRepository.findAll()).thenReturn(parts);
        mockMvc.perform(get("/Part/allParts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(parts.size()))
                .andDo(print());
    }

    @Test
    void shouldGetOnePart() throws  Exception{
        long id = 1;
        Part part = new Part(id, "spiegel", 20.00, 200.00, 2);
        when(partRepository.findById(id)).thenReturn(Optional.of(part));
        mockMvc.perform(get("/Part/Part/1", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(part.getName()))
                .andExpect(jsonPath("$.cost").value(part.getCost()))
                .andExpect(jsonPath("$.salesprice").value(part.getSalesprice()))
                .andExpect(jsonPath("$.stock").value(part.getStock()))
                .andDo(print());
    }

    @Test
    void shouldCreatePart() throws Exception {
        long id = 1;
        Part part = new Part(id, "spiegel", 20.00, 200.00, 2);
        mockMvc.perform(post("/Part/newPart").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(part)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void shouldUpdatePart() throws Exception {
        long id = 1;
        Part part = new Part(id, "spiegel", 20.00, 200.00, 2);
        Part updatedPart = new Part(id, "zuiger", 200.00, 300.00, 6);
        when(partRepository.findById(id)).thenReturn(Optional.of(part));
        when(partRepository.save(any(Part.class))).thenReturn(updatedPart);
        mockMvc.perform(put("/Part/Part/1", id).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedPart)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(updatedPart.getName()))
                .andExpect(jsonPath("$.cost").value(updatedPart.getCost()))
                .andExpect(jsonPath("$.salesprice").value(updatedPart.getSalesprice()))
                .andExpect(jsonPath("$.stock").value(updatedPart.getStock()))
                .andDo(print());
    }
}
