package com.luca.AutogarageGianlucaMeens.ControllerTest;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luca.AutogarageGianlucaMeens.Car.Car;
import com.luca.AutogarageGianlucaMeens.Car.CarController;
import com.luca.AutogarageGianlucaMeens.Car.CarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
public class CarControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarController carController;

    @MockBean
    private CarRepository carRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @WithMockUser(username = "super", password = "super123")
     void shouldGetAllCars() throws  Exception{
        List<Car> cars = new ArrayList<>(
                Arrays.asList(  new Car(1, "abcdef", "audi", "a3", 2002),
                                new Car(2, "abcdef", "audi", "a3", 2004)));
        when(carRepository.findAll()).thenReturn(cars);
        mockMvc.perform(get("/Autos/allAutos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(cars.size()))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "super", password = "super123")
    void shouldGetOneCar() throws  Exception{
        long id = 1;
        Car car = new Car(id, "abcdef", "audi", "a3", 2002);
        when(carRepository.findById(id)).thenReturn(Optional.of(car));
        mockMvc.perform(get("/Autos/allAutos/1", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.licensePlate").value(car.getLicensePlate()))
                .andExpect(jsonPath("$.brand").value(car.getBrand()))
                .andExpect(jsonPath("$.model").value(car.getModel()))
                .andExpect(jsonPath("$.versionYear").value(car.getVersionYear()))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "super", password = "super123")
    void shouldCreateCar() throws Exception {
        Car car = new Car(1, "abcdef", "audi", "a3", 2002);
        mockMvc.perform(post("/api/tutorials").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(car)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "super", password = "super123")
    void shouldUpdateTutorial() throws Exception {
        long id = 1;
        Car car = new Car(1, "abcdef", "audi", "a3", 2002);
        Car updatedcar = new Car(1, "abcdef", "audi", "a3", 2005);
        when(carRepository.findById(id)).thenReturn(Optional.of(car));
        when(carRepository.save(any(Car.class))).thenReturn(updatedcar);
        mockMvc.perform(put("/api/tutorials/{id}", id).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedcar)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.licensePlate").value(updatedcar.getLicensePlate()))
                .andExpect(jsonPath("$.brand").value(updatedcar.getBrand()))
                .andExpect(jsonPath("$.model").value(updatedcar.getModel()))
                .andExpect(jsonPath("$.versionYear").value(updatedcar.getVersionYear()))
                .andDo(print());
    }

}
