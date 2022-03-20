package com.luca.AutogarageGianlucaMeens.klant;

import com.luca.AutogarageGianlucaMeens.Exceptions.CustomerNotFoundException;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/Klanten")
public class CustomerController {

    private CustomerRepository customerRepository;

    CustomerController(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @PostMapping("/newCustomer")
    Customer newCustomer(@RequestBody Customer newCustomer) {
        return customerRepository.save(newCustomer);
    }

    @GetMapping(path="/allCustomers")
    public @ResponseBody Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/Customer/{id}")
    Customer getOneCustomer(@PathVariable Long id) {

        return customerRepository.findById(id)
                .orElseThrow(() ->new CustomerNotFoundException(id));
    }

    @PutMapping("/Customer/{id}")
    Customer replaceEmployee(@RequestBody Customer newCustomer, @PathVariable Long id) {

        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setName(newCustomer.getName());
                    customer.setLastName(customer.getLastName());
                    customer.setEmail(customer.getEmail());
                    customer.setPhoneNumber(customer.getPhoneNumber());
                    return customerRepository.save(customer);
                })
                .orElseGet(() -> {
                    newCustomer.setId(id);
                    return customerRepository.save(newCustomer);
                });
    }

    @DeleteMapping("/Customer/{id}")
    void deleteEmployee(@PathVariable Long id) {
        customerRepository.deleteById(id);
    }
}
