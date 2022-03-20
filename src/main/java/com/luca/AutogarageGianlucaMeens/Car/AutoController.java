package com.luca.AutogarageGianlucaMeens.Car;

import com.luca.AutogarageGianlucaMeens.Exceptions.AutoNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/Autos")
public class AutoController {

    private AutoRepository autoRepository;

    AutoController(AutoRepository autoRepository){
        this.autoRepository = autoRepository;
    }

    @PostMapping("/newAuto")
    Car newAuto(@RequestBody Car newCar) {
        return autoRepository.save(newCar);
    }

    @GetMapping(path="/allAutos")
    public @ResponseBody Iterable<Car> getAllAutos() {
        return autoRepository.findAll();
    }

    @GetMapping("/Auto/{id}")
    Car one(@PathVariable Long id) {

        return autoRepository.findById(id)
                .orElseThrow(() ->new AutoNotFoundException(id));
    }

    @PutMapping("/Auto/{id}")
    Car ReplaceAuto(@RequestBody Car newCar, @PathVariable Long id) {

        return autoRepository.findById(id)
                .map(Car -> {
                    Car.setOwnerID(Car.getOwnerID());
                    Car.setLicensePlate((Car.getLicensePlate()));
                    Car.setBrand(Car.getBrand());
                    Car.setModel(Car.getModel());
                    Car.setVersionYear(Car.getVersionYear());
                    Car.setAutopapieren(Car.getAutopapieren());
                    return autoRepository.save(Car);
                })
                .orElseGet(() -> {
                    newCar.setId(id);
                    return autoRepository.save(newCar);
                });
    }

    @DeleteMapping("/Auto/{id}")
    void deleteEmployee(@PathVariable Long id) {
        autoRepository.deleteById(id);
    }

}
