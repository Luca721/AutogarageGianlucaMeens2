package com.luca.AutogarageGianlucaMeens.Reperatie;


import com.luca.AutogarageGianlucaMeens.Exceptions.ReparatieNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/Reparaties")
public class ReparatieController {

    private ReparatieRepository reparatieRepository;

    ReparatieController(ReparatieRepository reparatieRepository){
        this.reparatieRepository = reparatieRepository;
    }

    @PostMapping("/newReparatie")
    Reparatie newReparatie(@RequestBody Reparatie newReparatie) {
        return reparatieRepository.save(newReparatie);
    }

    @GetMapping(path="/allreparaties")
    public @ResponseBody
    Iterable<Reparatie> getAllReparaties() {
        return reparatieRepository.findAll();
    }

    @GetMapping("/reparatie/{id}")
    Reparatie one(@PathVariable Long id) {

        return reparatieRepository.findById(id)
                .orElseThrow(() ->new ReparatieNotFoundException(id));
    }

    @PutMapping("/reparatie/{id}")
    Reparatie replaceReparatie(@RequestBody Reparatie newReparatie, @PathVariable Long id) {

        return reparatieRepository.findById(id)
                .map(Reparatie -> {
                    Reparatie.setSchade(Reparatie.getSchade());
                    Reparatie.setKosten(Reparatie.getKosten());
                    Reparatie.setPrijs(Reparatie.getPrijs());
                    return reparatieRepository.save(Reparatie);
                })
                .orElseGet(() -> {
                    newReparatie.setId(id);
                    return reparatieRepository.save(newReparatie);
                });
    }

    @DeleteMapping("/reparatie/{id}")
    void deleteReparatie(@PathVariable Long id) {
        reparatieRepository.deleteById(id);
    }

}
