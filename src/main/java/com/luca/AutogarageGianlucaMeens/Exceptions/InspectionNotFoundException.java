package com.luca.AutogarageGianlucaMeens.Exceptions;

public class InspectionNotFoundException extends RuntimeException {

    public InspectionNotFoundException(Long id) {
        super("Could not find Keuring " + id);
    }
}
