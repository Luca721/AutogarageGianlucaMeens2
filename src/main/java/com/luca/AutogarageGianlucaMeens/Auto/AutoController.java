package com.luca.AutogarageGianlucaMeens.Auto;

import com.luca.AutogarageGianlucaMeens.Exceptions.AutoNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/Autos")
public class AutoController {

    private AutoRepository autoRepository;

    @PostMapping("/newAuto")
    Auto newAuto(@RequestBody Auto newAuto) {
        return autoRepository.save(newAuto);
    }

    @GetMapping(path="/allAutos")
    public @ResponseBody Iterable<Auto> getAllAutos() {
        return autoRepository.findAll();
    }

    @GetMapping("/Auto/{id}")
    Auto one(@PathVariable Long id) {

        return autoRepository.findById(id)
                .orElseThrow(() ->new AutoNotFoundException(id));
    }

    @PutMapping("/Auto/{id}")
    Auto ReplaceAuto(@RequestBody Auto newAuto, @PathVariable Long id) {

        return autoRepository.findById(id)
                .map(Auto -> {
                    Auto.setEigenaarID(Auto.getEigenaarID());
                    Auto.setKenteken((Auto.getKenteken()));
                    Auto.setMerk(Auto.getMerk());
                    Auto.setModel(Auto.getModel());
                    Auto.setBouwjaar(Auto.getBouwjaar());
                    Auto.setAutopapieren(Auto.getAutopapieren());
                    return autoRepository.save(Auto);
                })
                .orElseGet(() -> {
                    newAuto.setId(id);
                    return autoRepository.save(newAuto);
                });
    }

    @DeleteMapping("/Auto/{id}")
    void deleteEmployee(@PathVariable Long id) {
        autoRepository.deleteById(id);
    }

}
