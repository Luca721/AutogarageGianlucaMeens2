package com.luca.AutogarageGianlucaMeens.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luca.AutogarageGianlucaMeens.Inspection.Inspection;
import com.luca.AutogarageGianlucaMeens.Inspection.InspectionController;
import com.luca.AutogarageGianlucaMeens.Inspection.InspectionRepository;
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

@WebMvcTest(InspectionController.class)
public class InspectionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InspectionRepository inspectionRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldGetAllInspections() throws  Exception{
        long id1 = 1;
        long id2 = 2;
        List<Inspection> inspections = new ArrayList<>(
                Arrays.asList(  new Inspection(id1, "spiegel afgebroken", 250.00),
                        new Inspection(id2, "zuiger door motorkap geschoten", 3499.99)));
        when(inspectionRepository.findAll()).thenReturn(inspections);
        mockMvc.perform(get("/Inspection/allInspection"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(inspections.size()))
                .andDo(print());
    }

    @Test
    void shouldGetOneInspection() throws  Exception{
        long id = 1;
        Inspection inspection = new Inspection(id, "spiegel afgebroken", 250.00);
        when(inspectionRepository.findById(id)).thenReturn(Optional.of(inspection));
        mockMvc.perform(get("/Inspection/Inspection/1", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.damage").value(inspection.getDamage()))
                .andExpect(jsonPath("$.expectedPrice").value(inspection.getExpectedPrice()))
                .andDo(print());
    }

    @Test
    void shouldCreateInspection() throws Exception {
        long id = 1;
        Inspection inspection = new Inspection(id, "spiegel afgebroken", 250.00);
        mockMvc.perform(post("/Inspection/newInspection").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inspection)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void shouldUpdateInspection() throws Exception {
        long id = 1;
        Inspection inspection = new Inspection(id, "spiegel afgebroken", 250.00);
        Inspection updatedInspection = new Inspection(id, "spiegel afgebroken", 250.00);
        when(inspectionRepository.findById(id)).thenReturn(Optional.of(inspection));
        when(inspectionRepository.save(any(Inspection.class))).thenReturn(updatedInspection);
        mockMvc.perform(put("/Inspection/Inspection/1", id).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedInspection)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.damage").value(updatedInspection.getDamage()))
                .andExpect(jsonPath("$.expectedPrice").value(updatedInspection.getExpectedPrice()))
                .andDo(print());
    }
}
