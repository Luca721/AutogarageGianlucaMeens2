package com.luca.AutogarageGianlucaMeens.Auto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(path = "/garage")
public class AutoController {

    private AutoRepository autoRepository;

    @PostMapping(path = "/addAutos")
    public @ResponseBody String addNewAuto(@RequestParam String kenteken
            , @RequestParam String merk
            , @RequestParam String model
            , @RequestParam int bouwjaar){

        Auto auto = new Auto();
        auto.setKenteken(kenteken);
        auto.setMerk(merk);
        auto.setModel(model);
        auto.setBouwjaar(bouwjaar);
        return "saved";
    }

    @GetMapping("/autos")
    public @ResponseBody Iterable<Auto> GetAllAutos() {
        return autoRepository.findAll();
    }

    @ExceptionHandler
    void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }
}
