package com.luca.AutogarageGianlucaMeens.CarPaper;

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
@RequestMapping(path = "/Carpapers")
public class CarPaperController {

    @Autowired
    private CarPaperService carPaperService;

    @PostMapping("/PostCarPapers")
    public Response uploadFile(@RequestParam("file") MultipartFile file) {
        CarPapers carPapers = carPaperService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(carPapers.getFileName())
                .toUriString();

        return new Response(carPapers.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @GetMapping("/GetCarpapers/{id}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long id, HttpServletRequest request) {
        // Load file as Resource
        CarPapers databaseFile = carPaperService.getFile(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(databaseFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + databaseFile.getFileName() + "\"")
                .body(new ByteArrayResource(databaseFile.getData()));
    }

    @DeleteMapping("/Carpapers/{id}")
    void deleteCarPapers(@PathVariable Long id) {
        carPaperService.DelFile(id);
    }
}
