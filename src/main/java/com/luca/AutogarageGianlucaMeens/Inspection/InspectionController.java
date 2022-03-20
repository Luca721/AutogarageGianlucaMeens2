package com.luca.AutogarageGianlucaMeens.Inspection;

import com.luca.AutogarageGianlucaMeens.Exceptions.InspectionNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/Keuringen")
public class InspectionController {

    private InspectionRepository inspectionRepository;

    InspectionController(InspectionRepository inspectionRepository){
        this.inspectionRepository = inspectionRepository;
    }

    @PostMapping("/newKeuring")
    Inspection newInspection(@RequestBody Inspection newInspection) {
        return inspectionRepository.save(newInspection);
    }

    @GetMapping(path="/allKeuringen")
    public @ResponseBody
    Iterable<Inspection> getAllInspection() {
        return inspectionRepository.findAll();
    }

    @GetMapping("/Keuring/{id}")
    Inspection getOneInspection(@PathVariable Long id) {

        return inspectionRepository.findById(id)
                .orElseThrow(() ->new InspectionNotFoundException(id));
    }

    @PutMapping("/Keuring/{id}")
    Inspection replaceInspection(@RequestBody Inspection newInspection, @PathVariable Long id) {

        return inspectionRepository.findById(id)
                .map(Inspection -> {
                    Inspection.setAutoID(Inspection.getAutoID());
                    Inspection.setDamage(Inspection.getDamage());
                    Inspection.setExpectedPrice(Inspection.getExpectedPrice());
                    Inspection.setRepairAccept(Inspection.getRepairAccept());
                    return inspectionRepository.save(Inspection);
                })
                .orElseGet(() -> {
                    newInspection.setId(id);
                    return inspectionRepository.save(newInspection);
                });
    }

    @DeleteMapping("/Keuring/{id}")
    void deleteInspection(@PathVariable Long id) {
        inspectionRepository.deleteById(id);
    }

}
