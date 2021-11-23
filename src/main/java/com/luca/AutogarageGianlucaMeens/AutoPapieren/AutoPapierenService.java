package com.luca.AutogarageGianlucaMeens.AutoPapieren;

import com.luca.AutogarageGianlucaMeens.Exceptions.FileNotFoundException;
import com.luca.AutogarageGianlucaMeens.Exceptions.FileStorageException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class AutoPapierenService {

    private AutoPapierenRepository autoPapierenRepository;

        public AutoPapieren storeFile(MultipartFile file){


            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            try {
                AutoPapieren autoPapieren = new AutoPapieren(fileName, file.getContentType(), file.getBytes());

                return autoPapierenRepository.save(autoPapieren);
            }
            catch (IOException ioException){
                throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ioException);
            }

    }

    public AutoPapieren getFile(Long fileId) {
        return autoPapierenRepository.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File not found with id " + fileId));
    }

}
