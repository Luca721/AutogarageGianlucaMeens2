package com.luca.AutogarageGianlucaMeens.CarPaper;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class CarPaperService {

    @Autowired
    private CarPaperRepository carPaperRepository;

    public CarPapers storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            CarPapers dbFile = new CarPapers(fileName, file.getContentType(), file.getBytes());

            return carPaperRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public CarPapers getFile(Long fileId) {
        return carPaperRepository.findById(fileId)
                .orElseThrow(() -> new StorageFileNotFoundException(fileId));
    }

    public void DelFile(Long id){
        carPaperRepository.deleteById(id);
    }
}
