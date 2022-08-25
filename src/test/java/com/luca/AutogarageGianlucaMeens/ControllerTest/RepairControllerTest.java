package com.luca.AutogarageGianlucaMeens.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luca.AutogarageGianlucaMeens.Repair.Repair;
import com.luca.AutogarageGianlucaMeens.Repair.RepairController;
import com.luca.AutogarageGianlucaMeens.Repair.RepairRepository;
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

@WebMvcTest(RepairController.class)
public class RepairControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RepairRepository repairRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldGetAllRepairs() throws  Exception{
        long id1 = 1;
        long id2 = 2;
        List<Repair> repairs = new ArrayList<>(
                Arrays.asList(  new Repair(id1, "gebroken spiegel", 150.00, 250.00),
                        new Repair(id2, "zuiger door motorkap geschoten", 2500.00, 3509.90)));
        when(repairRepository.findAll()).thenReturn(repairs);
        mockMvc.perform(get("/Repairs/allRepair"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(repairs.size()))
                .andDo(print());
    }

    @Test
    void shouldGetOneRepair() throws  Exception{
        long id = 1;
        Repair repair = new Repair(id, "gebroken spiegel", 150.00, 250.00);
        when(repairRepository.findById(id)).thenReturn(Optional.of(repair));
        mockMvc.perform(get("/Repairs/Repair/1", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.damage").value(repair.getDamage()))
                .andExpect(jsonPath("$.costs").value(repair.getCosts()))
                .andExpect(jsonPath("$.price").value(repair.getPrice()))
                .andDo(print());
    }

    @Test
    void shouldCreateRepair() throws Exception {
        long id = 1;
        Repair repair = new Repair(id, "gebroken spiegel", 150.00, 250.00);
        mockMvc.perform(post("/Repairs/newRepair").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(repair)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void shouldUpdateRepair() throws Exception {
        long id = 1;
        Repair repair = new Repair(id, "gebroken spiegel", 150.00, 250.00);
        Repair updatedRepair = new Repair(id, "zuiger door motorkap geschoten", 2500.00, 3509.90);
        when(repairRepository.findById(id)).thenReturn(Optional.of(repair));
        when(repairRepository.save(any(Repair.class))).thenReturn(updatedRepair);
        mockMvc.perform(put("/Repairs/Repair/1", id).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedRepair)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.damage").value(updatedRepair.getDamage()))
                .andExpect(jsonPath("$.costs").value(updatedRepair.getCosts()))
                .andExpect(jsonPath("$.price").value(updatedRepair.getPrice()))
                .andDo(print());
    }
}
