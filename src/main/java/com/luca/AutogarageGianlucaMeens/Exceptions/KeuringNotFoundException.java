package com.luca.AutogarageGianlucaMeens.Exceptions;

public class KeuringNotFoundException extends RuntimeException {

    public KeuringNotFoundException(Long id) {
        super("Could not find Keuring " + id);
    }
}
