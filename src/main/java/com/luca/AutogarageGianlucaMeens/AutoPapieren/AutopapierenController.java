package com.luca.AutogarageGianlucaMeens.AutoPapieren;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping(path = "/AutoPapieren")
public class AutopapierenController {

    @Autowired
    private AutoPapierenService autoPapierenService;


    //
    //Post method om de autopapieren te uploaden
    //
    @PostMapping("/PostAutopapieren")
    public Response uploadFile(@RequestParam("File") MultipartFile file) {
        CarPapers carPapers = autoPapierenService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(carPapers.getFileName())
                .toUriString();

        return new Response(carPapers.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }


    //
    //Get method om de Autopapiern op te vragen
    //
    @GetMapping("/GetAutoPapieren/{id}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long id, HttpServletRequest request) {
        // Load file as Resource
        CarPapers databaseFile = autoPapierenService.getFile(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(databaseFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + databaseFile.getFileName() + "\"")
                .body(new ByteArrayResource(databaseFile.getData()));
    }


    //
    //Delete method om de autopapieren te verwijderen
    //
    @DeleteMapping("/AutoPapieren/{id}")
    void deleteAutoPapieren(@PathVariable Long id) {
        autoPapierenService.DelFile(id);
    }
}
