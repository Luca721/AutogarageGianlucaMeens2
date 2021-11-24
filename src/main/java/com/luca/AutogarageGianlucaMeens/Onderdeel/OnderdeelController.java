package com.luca.AutogarageGianlucaMeens.Onderdeel;

import com.luca.AutogarageGianlucaMeens.Exceptions.OnderdeelnotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/Onderdelen")
public class OnderdeelController {

    private OnderdeelRepository onderdeelRepository;

    OnderdeelController(OnderdeelRepository onderdeelRepository){
        this.onderdeelRepository = onderdeelRepository;
    }

    @PostMapping("/newOnderdeel")
    Onderdeel newOnderdeel(@RequestBody Onderdeel newOnderdeel) {
        return onderdeelRepository.save(newOnderdeel);
    }

    @GetMapping(path="/allOnderdelen")
    public @ResponseBody
    Iterable<Onderdeel> getAllOnderdelen() {
        return onderdeelRepository.findAll();
    }

    @GetMapping("/Onderdeel/{id}")
    Onderdeel one(@PathVariable Long id) {

        return onderdeelRepository.findById(id)
                .orElseThrow(() ->new OnderdeelnotFoundException(id));
    }

    @PutMapping("/Onderdeel/{id}")
    Onderdeel replaceOnderdeel(@RequestBody Onderdeel newOnderdeel, @PathVariable Long id) {

        return onderdeelRepository.findById(id)
                .map(Onderdeel -> {
                    Onderdeel.setNaam(Onderdeel.getNaam());
                    Onderdeel.setInkoopprijs(Onderdeel.getInkoopprijs());
                    Onderdeel.setVerkoopprijs(Onderdeel.getVerkoopprijs());
                    Onderdeel.setVoorraad(Onderdeel.getVoorraad());
                    return onderdeelRepository.save(Onderdeel);
                })
                .orElseGet(() -> {
                    newOnderdeel.setId(id);
                    return onderdeelRepository.save(newOnderdeel);
                });
    }

    @DeleteMapping("/Onderdeel/{id}")
    void deleteOnderdeel(@PathVariable Long id) {
        onderdeelRepository.deleteById(id);
    }

}
