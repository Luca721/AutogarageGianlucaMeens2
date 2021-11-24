package com.luca.AutogarageGianlucaMeens.Exceptions;

public class OnderdeelnotFoundException extends RuntimeException {

    public OnderdeelnotFoundException(Long id) { super("Could not find Klant " + id); }
}
