package com.luca.AutogarageGianlucaMeens.Customer;


public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(Long id) {
        super("Could not find Klant " + id);
    }
}
