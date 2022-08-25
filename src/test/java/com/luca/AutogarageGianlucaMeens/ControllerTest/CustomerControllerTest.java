package com.luca.AutogarageGianlucaMeens.ControllerTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luca.AutogarageGianlucaMeens.Customer.Customer;
import com.luca.AutogarageGianlucaMeens.Customer.CustomerController;
import com.luca.AutogarageGianlucaMeens.Customer.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerRepository customerRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldGetAllCustomers() throws  Exception{
        long id1 = 1;
        long id2 = 2;
        List<Customer> customers = new ArrayList<>(
                Arrays.asList(  new Customer(id1, "Luca", "Meens", "0612345678", "gmm.meens@belastingdienst.nl"),
                                new Customer(id2, "Tom", "Jansen", "0612345678", "abcd@defg.nl")));
        when(customerRepository.findAll()).thenReturn(customers);
        mockMvc.perform(get("/Customer/allCustomers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(customers.size()))
                .andDo(print());
    }

    @Test
    void shouldGetOneCustomer() throws  Exception{
        long id = 1;
        Customer customer = new Customer(id, "Luca", "Meens", "0612345678", "gmm.meens@belastingdienst.nl");
        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));
        mockMvc.perform(get("/Customer/Customer/1", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(customer.getName()))
                .andExpect(jsonPath("$.lastName").value(customer.getLastName()))
                .andExpect(jsonPath("$.phoneNumber").value(customer.getPhoneNumber()))
                .andExpect(jsonPath("$.email").value(customer.getEmail()))
                .andDo(print());
    }

    @Test
    void shouldCreateCustomer() throws Exception {
        long id = 1;
        Customer customer = new Customer(id, "Luca", "Meens", "0612345678", "gmm.meens@belastingdienst.nl");
        mockMvc.perform(post("/Customer/newCustomer").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void shouldUpdateTutorial() throws Exception {
        long id = 1;
        Customer customer = new Customer(id, "Luca", "Meens", "0612345678", "gmm.meens@belastingdienst.nl");
        Customer updatedcustomer = new Customer(id, "Arie", "Bartels", "0612345678", "a.bartels@belastingdienst.nl");
        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(updatedcustomer);
        mockMvc.perform(put("/Customer/Customer/1", id).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedcustomer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(updatedcustomer.getName()))
                .andExpect(jsonPath("$.lastName").value(updatedcustomer.getLastName()))
                .andExpect(jsonPath("$.phoneNumber").value(updatedcustomer.getPhoneNumber()))
                .andExpect(jsonPath("$.email").value(updatedcustomer.getEmail()))
                .andDo(print());
    }
}
