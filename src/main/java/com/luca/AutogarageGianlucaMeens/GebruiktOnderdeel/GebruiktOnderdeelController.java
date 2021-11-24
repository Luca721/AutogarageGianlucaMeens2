package com.luca.AutogarageGianlucaMeens.GebruiktOnderdeel;

import com.luca.AutogarageGianlucaMeens.Exceptions.GebruiktOnderdeelNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/GebruikteOnderdelen")
public class GebruiktOnderdeelController {

    private GebruiktOnderdeelRepository gebruiktOnderdeelRepository;

    GebruiktOnderdeelController(GebruiktOnderdeelRepository gebruiktOnderdeelRepository){
        this.gebruiktOnderdeelRepository = gebruiktOnderdeelRepository;
    }

    @PostMapping("/newGebruiktOnderdeel")
    GebruiktOnderdeel newGebruiktOnderdeel(@RequestBody GebruiktOnderdeel newGebruiktOnderdeel) {
        return gebruiktOnderdeelRepository.save(newGebruiktOnderdeel);
    }

    @GetMapping(path="/allGebruikteOnderdelen")
    public @ResponseBody
    Iterable<GebruiktOnderdeel> getAllGebruikteOnderdelen() {
        return gebruiktOnderdeelRepository.findAll();
    }

    @GetMapping("/GebruiktOnderdeel/{id}")
    GebruiktOnderdeel one(@PathVariable Long id) {

        return gebruiktOnderdeelRepository.findById(id)
                .orElseThrow(() ->new GebruiktOnderdeelNotFoundException(id));
    }

    @PutMapping("/GebruiktOnderdeel/{id}")
    GebruiktOnderdeel replaceGebruiktOnderdeel(@RequestBody GebruiktOnderdeel newGebruiktOndrdeel, @PathVariable Long id) {

        return gebruiktOnderdeelRepository.findById(id)
                .map(GebruiktOnderdeel -> {
                    GebruiktOnderdeel.setOnderdeelID(GebruiktOnderdeel.getOnderdeelID());
                    GebruiktOnderdeel.setReperatieID(GebruiktOnderdeel.getReperatieID());
                    GebruiktOnderdeel.setAantal(GebruiktOnderdeel.getAantal());
                    return gebruiktOnderdeelRepository.save(GebruiktOnderdeel);
                })
                .orElseGet(() -> {
                    newGebruiktOndrdeel.setId(id);
                    return gebruiktOnderdeelRepository.save(newGebruiktOndrdeel);
                });
    }

    @DeleteMapping("/GebruiktOnderdeel/{id}")
    void deleteGebruiktOnderdeel(@PathVariable Long id) {
        gebruiktOnderdeelRepository.deleteById(id);
    }

}
