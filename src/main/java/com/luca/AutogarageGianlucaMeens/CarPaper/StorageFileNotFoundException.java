package com.luca.AutogarageGianlucaMeens.CarPaper;

public class StorageFileNotFoundException extends RuntimeException {

    public StorageFileNotFoundException(Long id) {
        super("Could not find File " + id);
    }
}
