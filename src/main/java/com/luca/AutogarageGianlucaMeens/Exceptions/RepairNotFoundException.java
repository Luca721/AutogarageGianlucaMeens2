package com.luca.AutogarageGianlucaMeens.Exceptions;

public class RepairNotFoundException extends RuntimeException {

    public RepairNotFoundException(Long id) {
        super("Could not find reparatie " + id);
    }
}
