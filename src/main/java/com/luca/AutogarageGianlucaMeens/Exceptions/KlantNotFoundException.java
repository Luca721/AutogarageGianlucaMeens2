package com.luca.AutogarageGianlucaMeens.Exceptions;


public class KlantNotFoundException extends RuntimeException {

    public KlantNotFoundException(Long id) {
        super("Could not find Klant " + id);
    }
}
