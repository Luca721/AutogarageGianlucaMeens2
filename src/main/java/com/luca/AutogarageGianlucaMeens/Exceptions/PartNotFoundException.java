package com.luca.AutogarageGianlucaMeens.Exceptions;

public class PartNotFoundException extends RuntimeException {

    public PartNotFoundException(Long id) { super("Could not find Klant " + id); }
}
