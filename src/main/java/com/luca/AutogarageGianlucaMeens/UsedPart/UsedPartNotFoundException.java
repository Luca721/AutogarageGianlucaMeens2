package com.luca.AutogarageGianlucaMeens.UsedPart;

public class UsedPartNotFoundException extends RuntimeException {

    public UsedPartNotFoundException(Long id) {
        super("Could not find GebruiktOnderdeel " + id);
    }
}
