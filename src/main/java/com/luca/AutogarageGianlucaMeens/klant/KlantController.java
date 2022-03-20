package com.luca.AutogarageGianlucaMeens.klant;

import com.luca.AutogarageGianlucaMeens.Exceptions.KlantNotFoundException;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/Klanten")
public class KlantController {

    private KlantRepository klantRepository;

    KlantController(KlantRepository klantRepository){
        this.klantRepository = klantRepository;
    }

    @PostMapping("/NewKlant")
    Customer newKlant(@RequestBody Customer newCustomer) {
        return klantRepository.save(newCustomer);
    }

    @GetMapping(path="/allKlanten")
    public @ResponseBody Iterable<Customer> getAllUsers() {
        return klantRepository.findAll();
    }

    @GetMapping("/Klant/{id}")
    Customer one(@PathVariable Long id) {

        return klantRepository.findById(id)
                .orElseThrow(() ->new KlantNotFoundException(id));
    }

    @PutMapping("/Klant/{id}")
    Customer replaceEmployee(@RequestBody Customer newCustomer, @PathVariable Long id) {

        return klantRepository.findById(id)
                .map(customer -> {
                    customer.setName(newCustomer.getName());
                    customer.setLastName(customer.getLastName());
                    customer.setEmail(customer.getEmail());
                    customer.setPhoneNumber(customer.getPhoneNumber());
                    return klantRepository.save(customer);
                })
                .orElseGet(() -> {
                    newCustomer.setId(id);
                    return klantRepository.save(newCustomer);
                });
    }

    @DeleteMapping("/Klant/{id}")
    void deleteEmployee(@PathVariable Long id) {
        klantRepository.deleteById(id);
    }
}
