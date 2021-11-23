package com.luca.AutogarageGianlucaMeens.klant;

import com.luca.AutogarageGianlucaMeens.Auto.Auto;
import com.luca.AutogarageGianlucaMeens.Exceptions.KlantNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/garage")
public class KlantController {

    private KlantRepository klantRepository;

    KlantController(KlantRepository klantRepository){
        this.klantRepository = klantRepository;
    }


    @GetMapping(path="/all")
    public @ResponseBody Iterable<Klant> getAllUsers() {
        return klantRepository.findAll();
    }

    @PostMapping("/employees")
    Klant newKlant(@RequestBody Klant newKlant) {
        return klantRepository.save(newKlant);
    }

    @GetMapping("/employees/{id}")
    Klant one(@PathVariable Long id) {

        return klantRepository.findById(id)
                .orElseThrow(() ->new KlantNotFoundException(id));
    }

    @PutMapping("/employees/{id}")
    Klant replaceEmployee(@RequestBody Klant newKlant, @PathVariable Long id) {

        return klantRepository.findById(id)
                .map(klant -> {
                    klant.setNaam(newKlant.getNaam());
                    klant.setAchternaam(klant.getAchternaam());
                    klant.setEmail(klant.getEmail());
                    klant.setTelefoonNummer(klant.getTelefoonNummer());
                    return klantRepository.save(klant);
                })
                .orElseGet(() -> {
                    newKlant.setId(id);
                    return klantRepository.save(newKlant);
                });
    }

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        klantRepository.deleteById(id);
    }
}
