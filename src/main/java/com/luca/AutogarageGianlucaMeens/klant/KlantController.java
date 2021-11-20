package com.luca.AutogarageGianlucaMeens.klant;

import com.luca.AutogarageGianlucaMeens.Auto.Auto;
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

    @GetMapping("/klanten")
    List<Klant> all(){
        return klantRepository.findAll();
    }

    @PostMapping("/newKlant")
    Klant newKlant(@RequestBody Klant newKlant){
        return klantRepository.save(newKlant);
    }

//    @PostMapping(path = "/addKlant")
//    public @ResponseBody String addNewKlant(@RequestParam String naam,
//                                            @RequestParam String achternaam,
//                                            @RequestParam String email,
//                                            @RequestParam String telefoonnummer){
//        Klant klant = new Klant();
//        klant.setNaam(naam);
//        klant.setAchternaam(achternaam);
//        klant.setEmail(email);
//        klant.setTelefoonNummer(telefoonnummer);
//        return "saved";
//    }
//
//    @PostMapping("/books")
//    public String addBook(@RequestBody String naam,
//                                          String achternaam,
//                                          String email,
//                                          String telefoonnummer) {
//        Klant klant = new Klant();
//        klant.setNaam(naam);
//        klant.setAchternaam(achternaam);
//        klant.setEmail(email);
//        klant.setTelefoonNummer(telefoonnummer);
//
//        return "done";
//    }
//
//    @GetMapping("/klanten")
//    public ResponseEntity<List<Klant>> getAll() {
//        return new ResponseEntity<>(KlantService.getAll(), HttpStatus.OK);
//    }
//
//    @GetMapping("/klanten1")
//    public ResponseEntity<Object> getAllBooks() {
//        return ResponseEntity.ok(klantRepository);
//    }
}
