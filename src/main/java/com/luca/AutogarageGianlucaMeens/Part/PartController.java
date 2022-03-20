package com.luca.AutogarageGianlucaMeens.Part;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/Part")
public class PartController {

    private PartRepository partRepository;

    PartController(PartRepository partRepository){
        this.partRepository = partRepository;
    }

    @PostMapping("/newPart")
    part newPart(@RequestBody part newPart) {
        return partRepository.save(newPart);
    }

    @GetMapping(path="/allParts")
    public @ResponseBody
    Iterable<part> getAllPart() {
        return partRepository.findAll();
    }

    @GetMapping("/Part/{id}")
    part getOnePart(@PathVariable Long id) {

        return partRepository.findById(id)
                .orElseThrow(() ->new PartNotFoundException(id));
    }

    @PutMapping("/Onderdeel/{id}")
    part replacePart(@RequestBody part newPart, @PathVariable Long id) {

        return partRepository.findById(id)
                .map(part -> {
                    part.setName(part.getName());
                    part.setCost(part.getCost());
                    part.setSalesprice(part.getSalesprice());
                    part.setStock(part.getStock());
                    return partRepository.save(part);
                })
                .orElseGet(() -> {
                    newPart.setId(id);
                    return partRepository.save(newPart);
                });
    }

    @DeleteMapping("/Onderdeel/{id}")
    void deletePart(@PathVariable Long id) {
        partRepository.deleteById(id);
    }

}
