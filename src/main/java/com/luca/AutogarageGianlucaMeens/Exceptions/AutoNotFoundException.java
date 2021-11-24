package com.luca.AutogarageGianlucaMeens.Exceptions;

public class AutoNotFoundException extends RuntimeException {

    public AutoNotFoundException(Long id) {
        super("Could not find Auto " + id);
    }
}
