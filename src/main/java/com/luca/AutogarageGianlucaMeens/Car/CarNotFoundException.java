package com.luca.AutogarageGianlucaMeens.Car;

public class CarNotFoundException extends RuntimeException {

    public CarNotFoundException(Long id) {
        super("Could not find Auto " + id);
    }
}
