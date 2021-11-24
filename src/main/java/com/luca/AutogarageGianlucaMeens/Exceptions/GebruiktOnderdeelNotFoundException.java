package com.luca.AutogarageGianlucaMeens.Exceptions;

public class GebruiktOnderdeelNotFoundException extends RuntimeException {

    public GebruiktOnderdeelNotFoundException(Long id) {
        super("Could not find GebruiktOnderdeel " + id);
    }
}
