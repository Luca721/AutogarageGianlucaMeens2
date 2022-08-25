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
    Part newPart(@RequestBody Part newPart) {
        return partRepository.save(newPart);
    }

    @GetMapping(path="/allParts")
    public @ResponseBody
    Iterable<Part> getAllPart() {
        return partRepository.findAll();
    }

    @GetMapping("/Part/{id}")
    Part getOnePart(@PathVariable Long id) {

        return partRepository.findById(id)
                .orElseThrow(() ->new PartNotFoundException(id));
    }

    @PutMapping("/Part/{id}")
    Part replacePart(@RequestBody Part newPart, @PathVariable Long id) {

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

    @DeleteMapping("/Part/{id}")
    void deletePart(@PathVariable Long id) {
        partRepository.deleteById(id);
    }

}
