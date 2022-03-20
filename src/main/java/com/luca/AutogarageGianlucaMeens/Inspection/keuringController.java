package com.luca.AutogarageGianlucaMeens.Inspection;

import com.luca.AutogarageGianlucaMeens.Exceptions.InspectionNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/Keuringen")
public class keuringController {

    private KeuringRepository keuringRepository;

    keuringController(KeuringRepository keuringRepository){
        this.keuringRepository = keuringRepository;
    }

    @PostMapping("/newKeuring")
    Inspection newKeuring(@RequestBody Inspection newInspection) {
        return keuringRepository.save(newInspection);
    }

    @GetMapping(path="/allKeuringen")
    public @ResponseBody
    Iterable<Inspection> getAllKeuringen() {
        return keuringRepository.findAll();
    }

    @GetMapping("/Keuring/{id}")
    Inspection one(@PathVariable Long id) {

        return keuringRepository.findById(id)
                .orElseThrow(() ->new InspectionNotFoundException(id));
    }

    @PutMapping("/Keuring/{id}")
    Inspection replaceKeuring(@RequestBody Inspection newInspection, @PathVariable Long id) {

        return keuringRepository.findById(id)
                .map(Inspection -> {
                    Inspection.setAutoID(Inspection.getAutoID());
                    Inspection.setDamage(Inspection.getDamage());
                    Inspection.setExpectedPrice(Inspection.getExpectedPrice());
                    Inspection.setReperatieGoedkeuring(Inspection.getReperatieGoedkeuring());
                    return keuringRepository.save(Inspection);
                })
                .orElseGet(() -> {
                    newInspection.setId(id);
                    return keuringRepository.save(newInspection);
                });
    }

    @DeleteMapping("/Keuring/{id}")
    void deleteKeuring(@PathVariable Long id) {
        keuringRepository.deleteById(id);
    }

}
