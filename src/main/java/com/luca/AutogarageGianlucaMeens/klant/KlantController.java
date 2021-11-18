package com.luca.AutogarageGianlucaMeens.klant;

import com.luca.AutogarageGianlucaMeens.Auto.Auto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(path = "/garage")
public class KlantController {

    private KlantRepository klantRepository;

    @PostMapping(path = "/addKlant")
    public @ResponseBody String addNewKlant(@RequestParam String naam,
                                            @RequestParam String achternaam,
                                            @RequestParam String email,
                                            @RequestParam String telefoonnummer){
        Klant klant = new Klant();
        klant.setNaam(naam);
        klant.setAchternaam(achternaam);
        klant.setEmail(email);
        klant.setTelefoonNummer(telefoonnummer);
        return "saved";
    }

    @PostMapping("/books")
    public ResponseEntity<Object> addBook(@RequestBody String naam,
                                          String achternaam,
                                          String email,
                                          String telefoonnummer) {
        Klant klant = new Klant();
        klant.setNaam(naam);
        klant.setAchternaam(achternaam);
        klant.setEmail(email);
        klant.setTelefoonNummer(telefoonnummer);

        return ResponseEntity.created();
    }

    @GetMapping("/klanten/{id}")
    public @ResponseBody Iterable<Klant> GetAllAutos() {
        return klantRepository.findAll();
    }

    @GetMapping("/klanten1")
    public ResponseEntity<Object> getAllBooks() {
        return ResponseEntity.ok(klantRepository);
    }
}
