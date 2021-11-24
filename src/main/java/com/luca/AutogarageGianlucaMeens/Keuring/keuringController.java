package com.luca.AutogarageGianlucaMeens.Keuring;

import com.luca.AutogarageGianlucaMeens.Exceptions.KeuringNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/Keuringen")
public class keuringController {

    private KeuringRepository keuringRepository;

    keuringController(KeuringRepository keuringRepository){
        this.keuringRepository = keuringRepository;
    }

    @PostMapping("/newKeuring")
    Keuring newKeuring(@RequestBody Keuring newKeuring) {
        return keuringRepository.save(newKeuring);
    }

    @GetMapping(path="/allKeuringen")
    public @ResponseBody
    Iterable<Keuring> getAllKeuringen() {
        return keuringRepository.findAll();
    }

    @GetMapping("/Keuring/{id}")
    Keuring one(@PathVariable Long id) {

        return keuringRepository.findById(id)
                .orElseThrow(() ->new KeuringNotFoundException(id));
    }

    @PutMapping("/Keuring/{id}")
    Keuring replaceKeuring(@RequestBody Keuring newKeuring, @PathVariable Long id) {

        return keuringRepository.findById(id)
                .map(Keuring -> {
                    Keuring.setAutoID(Keuring.getAutoID());
                    Keuring.setSchade(Keuring.getSchade());
                    Keuring.setVerwachtePrijs(Keuring.getVerwachtePrijs());
                    Keuring.setReperatieGoedkeuring(Keuring.getReperatieGoedkeuring());
                    return keuringRepository.save(Keuring);
                })
                .orElseGet(() -> {
                    newKeuring.setId(id);
                    return keuringRepository.save(newKeuring);
                });
    }

    @DeleteMapping("/Keuring/{id}")
    void deleteKeuring(@PathVariable Long id) {
        keuringRepository.deleteById(id);
    }

}
