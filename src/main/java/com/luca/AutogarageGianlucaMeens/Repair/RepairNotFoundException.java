package com.luca.AutogarageGianlucaMeens.Repair;

public class RepairNotFoundException extends RuntimeException {

    public RepairNotFoundException(Long id) {
        super("Could not find reparatie " + id);
    }
}
