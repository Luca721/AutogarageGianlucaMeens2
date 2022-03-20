package com.luca.AutogarageGianlucaMeens.UsedPart;

import com.luca.AutogarageGianlucaMeens.Exceptions.UsedPartNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/GebruikteOnderdelen")
public class GebruiktOnderdeelController {

    private GebruiktOnderdeelRepository gebruiktOnderdeelRepository;

    GebruiktOnderdeelController(GebruiktOnderdeelRepository gebruiktOnderdeelRepository){
        this.gebruiktOnderdeelRepository = gebruiktOnderdeelRepository;
    }

    @PostMapping("/newGebruiktOnderdeel")
    UsedPart newGebruiktOnderdeel(@RequestBody UsedPart newUsedPart) {
        return gebruiktOnderdeelRepository.save(newUsedPart);
    }

    @GetMapping(path="/allGebruikteOnderdelen")
    public @ResponseBody
    Iterable<UsedPart> getAllGebruikteOnderdelen() {
        return gebruiktOnderdeelRepository.findAll();
    }

    @GetMapping("/GebruiktOnderdeel/{id}")
    UsedPart one(@PathVariable Long id) {

        return gebruiktOnderdeelRepository.findById(id)
                .orElseThrow(() ->new UsedPartNotFoundException(id));
    }

    @PutMapping("/GebruiktOnderdeel/{id}")
    UsedPart replaceGebruiktOnderdeel(@RequestBody UsedPart newGebruiktOndrdeel, @PathVariable Long id) {

        return gebruiktOnderdeelRepository.findById(id)
                .map(UsedPart -> {
                    UsedPart.setPartID(UsedPart.getPartID());
                    UsedPart.setRepairID(UsedPart.getRepairID());
                    UsedPart.setAmount(UsedPart.getAmount());
                    return gebruiktOnderdeelRepository.save(UsedPart);
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
