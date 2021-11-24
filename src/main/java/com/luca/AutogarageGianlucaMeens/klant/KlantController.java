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
    Klant newKlant(@RequestBody Klant newKlant) {
        return klantRepository.save(newKlant);
    }

    @GetMapping(path="/allKlanten")
    public @ResponseBody Iterable<Klant> getAllUsers() {
        return klantRepository.findAll();
    }

    @GetMapping("/Klant/{id}")
    Klant one(@PathVariable Long id) {

        return klantRepository.findById(id)
                .orElseThrow(() ->new KlantNotFoundException(id));
    }

    @PutMapping("/Klant/{id}")
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

    @DeleteMapping("/Klant/{id}")
    void deleteEmployee(@PathVariable Long id) {
        klantRepository.deleteById(id);
    }
}
