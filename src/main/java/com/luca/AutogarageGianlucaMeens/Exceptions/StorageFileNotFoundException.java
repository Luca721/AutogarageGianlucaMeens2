package com.luca.AutogarageGianlucaMeens.Exceptions;

public class StorageFileNotFoundException extends RuntimeException {

    public StorageFileNotFoundException(Long id) {
        super("Could not find File " + id);
    }
}
