package com.luca.AutogarageGianlucaMeens.Exceptions;

public class CarNotFoundException extends RuntimeException {

    public CarNotFoundException(Long id) {
        super("Could not find Auto " + id);
    }
}
