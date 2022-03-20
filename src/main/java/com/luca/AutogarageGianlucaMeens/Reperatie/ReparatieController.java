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
    Repair newReparatie(@RequestBody Repair newRepair) {
        return reparatieRepository.save(newRepair);
    }

    @GetMapping(path="/allreparaties")
    public @ResponseBody
    Iterable<Repair> getAllReparaties() {
        return reparatieRepository.findAll();
    }

    @GetMapping("/reparatie/{id}")
    Repair one(@PathVariable Long id) {

        return reparatieRepository.findById(id)
                .orElseThrow(() ->new ReparatieNotFoundException(id));
    }

    @PutMapping("/reparatie/{id}")
    Repair replaceReparatie(@RequestBody Repair newRepair, @PathVariable Long id) {

        return reparatieRepository.findById(id)
                .map(Repair -> {
                    Repair.setDamage(Repair.getDamage());
                    Repair.setCosts(Repair.getCosts());
                    Repair.setPrice(Repair.getPrice());
                    return reparatieRepository.save(Repair);
                })
                .orElseGet(() -> {
                    newRepair.setId(id);
                    return reparatieRepository.save(newRepair);
                });
    }

    @DeleteMapping("/reparatie/{id}")
    void deleteReparatie(@PathVariable Long id) {
        reparatieRepository.deleteById(id);
    }

}
