package com.luca.AutogarageGianlucaMeens.Car;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/Autos")
public class CarController {

    private CarRepository carRepository;

    CarController(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    @PostMapping("/newAuto")
    Car newCar(@RequestBody Car newCar) {
        return carRepository.save(newCar);
    }

    @GetMapping(path="/allAutos")
    public @ResponseBody Iterable<Car> getAllCars() {
        return carRepository.findAll();
    }

    @GetMapping("/Auto/{id}")
    Car one(@PathVariable Long id) {

        return carRepository.findById(id)
                .orElseThrow(() ->new CarNotFoundException(id));
    }

    @PutMapping("/Auto/{id}")
    Car ReplaceCar(@RequestBody Car newCar, @PathVariable Long id) {

        return carRepository.findById(id)
                .map(Car -> {
                    Car.setOwnerID(Car.getOwnerID());
                    Car.setLicensePlate((Car.getLicensePlate()));
                    Car.setBrand(Car.getBrand());
                    Car.setModel(Car.getModel());
                    Car.setVersionYear(Car.getVersionYear());
                    Car.setCarPapers(Car.getCarPapers());
                    return carRepository.save(Car);
                })
                .orElseGet(() -> {
                    newCar.setId(id);
                    return carRepository.save(newCar);
                });
    }

    @DeleteMapping("/Auto/{id}")
    void deleteCar(@PathVariable Long id) {
        carRepository.deleteById(id);
    }

}
