package com.luca.AutogarageGianlucaMeens.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luca.AutogarageGianlucaMeens.UsedPart.UsedPart;
import com.luca.AutogarageGianlucaMeens.UsedPart.UsedPartController;
import com.luca.AutogarageGianlucaMeens.UsedPart.UsedPartRepository;
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

@WebMvcTest(UsedPartController.class)
public class UsedPartControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsedPartRepository usedPartRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldGetAllUsedParts() throws  Exception{
        long id1 = 1;
        long id2 = 2;
        List<UsedPart> usedParts = new ArrayList<>(
                Arrays.asList(  new UsedPart(id1, 3),
                        new UsedPart(id2, 5)));
        when(usedPartRepository.findAll()).thenReturn(usedParts);
        mockMvc.perform(get("/UsedParts/allUsedParts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(usedParts.size()))
                .andDo(print());
    }

    @Test
    void shouldGetOneUsedPart() throws  Exception{
        long id = 1;
        UsedPart usedPart = new UsedPart(id, 3);
        when(usedPartRepository.findById(id)).thenReturn(Optional.of(usedPart));
        mockMvc.perform(get("/UsedParts/UsedPart/1", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.amount").value(usedPart.getAmount()))
                .andDo(print());
    }

    @Test
    void shouldCreateUsedPart() throws Exception {
        long id = 1;
        UsedPart usedPart = new UsedPart(id, 3);
        mockMvc.perform(post("/UsedParts/newUsedPart").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usedPart)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void shouldUpdateUsedPart() throws Exception {
        long id = 1;
        UsedPart usedPart = new UsedPart(id, 3);
        UsedPart updatedUsedPart = new UsedPart(id, 5);
        when(usedPartRepository.findById(id)).thenReturn(Optional.of(updatedUsedPart));
        when(usedPartRepository.save(any(UsedPart.class))).thenReturn(updatedUsedPart);
        mockMvc.perform(put("/UsedParts/UsedPart/1", id).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedUsedPart)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.amount").value(updatedUsedPart.getAmount()))
                .andDo(print());
    }
}
