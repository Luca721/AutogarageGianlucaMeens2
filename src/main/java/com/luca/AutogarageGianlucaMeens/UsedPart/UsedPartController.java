package com.luca.AutogarageGianlucaMeens.UsedPart;

import com.luca.AutogarageGianlucaMeens.Exceptions.UsedPartNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/UsedParts")
public class UsedPartController {

    private UsedPartRepository usedPartRepository;

    UsedPartController(UsedPartRepository usedPartRepository){
        this.usedPartRepository = usedPartRepository;
    }

    @PostMapping("/newUsedPart")
    UsedPart newUsedPart(@RequestBody UsedPart newUsedPart) {
        return usedPartRepository.save(newUsedPart);
    }

    @GetMapping(path="/allUsedParts")
    public @ResponseBody
    Iterable<UsedPart> getAllUsedParts() {
        return usedPartRepository.findAll();
    }

    @GetMapping("/UsedPart/{id}")
    UsedPart getOneUsedPart(@PathVariable Long id) {

        return usedPartRepository.findById(id)
                .orElseThrow(() ->new UsedPartNotFoundException(id));
    }

    @PutMapping("/UsedPart/{id}")
    UsedPart replaceUsedPart(@RequestBody UsedPart newUsedPart, @PathVariable Long id) {

        return usedPartRepository.findById(id)
                .map(UsedPart -> {
                    UsedPart.setPartID(UsedPart.getPartID());
                    UsedPart.setRepairID(UsedPart.getRepairID());
                    UsedPart.setAmount(UsedPart.getAmount());
                    return usedPartRepository.save(UsedPart);
                })
                .orElseGet(() -> {
                    newUsedPart.setId(id);
                    return usedPartRepository.save(newUsedPart);
                });
    }

    @DeleteMapping("/UsedPart/{id}")
    void deleteUsedPart(@PathVariable Long id) {
        usedPartRepository.deleteById(id);
    }

}
