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


    @GetMapping(path="/all")
    public @ResponseBody Iterable<Klant> getAllUsers() {
        return klantRepository.findAll();
    }

    @PostMapping("/employees")
    Klant newKlant(@RequestBody Klant newKlant) {
        return klantRepository.save(newKlant);
    }

//    @GetMapping("/employees/{id}")
//    Klant one(@PathVariable Long id) {
//
//        return klantRepository.findById(id)
//                .orElseThrow(() -> new KlantNotFou(id));
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
