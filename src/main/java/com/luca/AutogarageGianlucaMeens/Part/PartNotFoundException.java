package com.luca.AutogarageGianlucaMeens.Part;

public class PartNotFoundException extends RuntimeException {

    public PartNotFoundException(Long id) { super("Could not find Klant " + id); }
}
