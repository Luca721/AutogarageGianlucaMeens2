package com.luca.AutogarageGianlucaMeens.Onderdeel;

import com.luca.AutogarageGianlucaMeens.Exceptions.PartNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/Onderdelen")
public class OnderdeelController {

    private OnderdeelRepository onderdeelRepository;

    OnderdeelController(OnderdeelRepository onderdeelRepository){
        this.onderdeelRepository = onderdeelRepository;
    }

    @PostMapping("/newOnderdeel")
    part newOnderdeel(@RequestBody part newPart) {
        return onderdeelRepository.save(newPart);
    }

    @GetMapping(path="/allOnderdelen")
    public @ResponseBody
    Iterable<part> getAllOnderdelen() {
        return onderdeelRepository.findAll();
    }

    @GetMapping("/Onderdeel/{id}")
    part one(@PathVariable Long id) {

        return onderdeelRepository.findById(id)
                .orElseThrow(() ->new PartNotFoundException(id));
    }

    @PutMapping("/Onderdeel/{id}")
    part replaceOnderdeel(@RequestBody part newPart, @PathVariable Long id) {

        return onderdeelRepository.findById(id)
                .map(part -> {
                    part.setName(part.getName());
                    part.setCost(part.getCost());
                    part.setSalesprice(part.getSalesprice());
                    part.setStock(part.getStock());
                    return onderdeelRepository.save(part);
                })
                .orElseGet(() -> {
                    newPart.setId(id);
                    return onderdeelRepository.save(newPart);
                });
    }

    @DeleteMapping("/Onderdeel/{id}")
    void deleteOnderdeel(@PathVariable Long id) {
        onderdeelRepository.deleteById(id);
    }

}
