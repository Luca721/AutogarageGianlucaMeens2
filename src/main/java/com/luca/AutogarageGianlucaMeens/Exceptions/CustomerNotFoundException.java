package com.luca.AutogarageGianlucaMeens.Exceptions;


public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(Long id) {
        super("Could not find Klant " + id);
    }
}
