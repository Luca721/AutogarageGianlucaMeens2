package com.luca.AutogarageGianlucaMeens.Exceptions;

public class ReparatieNotFoundException extends RuntimeException {

    public ReparatieNotFoundException(Long id) {
        super("Could not find reparatie " + id);
    }
}
