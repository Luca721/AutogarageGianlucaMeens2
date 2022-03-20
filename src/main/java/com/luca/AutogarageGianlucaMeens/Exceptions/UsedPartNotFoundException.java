package com.luca.AutogarageGianlucaMeens.Exceptions;

public class UsedPartNotFoundException extends RuntimeException {

    public UsedPartNotFoundException(Long id) {
        super("Could not find GebruiktOnderdeel " + id);
    }
}
