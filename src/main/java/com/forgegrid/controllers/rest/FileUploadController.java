package com.forgegrid.controllers.rest;

import com.forgegrid.bussines.service.StorageService;
import com.forgegrid.validation.annotations.ValidFile;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.security.Principal;

@RestController
@RequestMapping("/rest/file")
@CrossOrigin("${front.web.url}")
public class FileUploadController {

    private final StorageService storageService;

    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/upload")
    public void uploadFile(@ValidFile @RequestParam("file") MultipartFile file, Principal principal) {
        try {
            storageService.saveFileForUsername(file, principal.getName());
        } catch (IOException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INSUFFICIENT_STORAGE, "IO error happened while saving file");
        }
    }

    // TODO: Add file name validation annotation here
    @GetMapping("/retrieve/{fileName:.+}")
    public Resource retrieveFile(@Valid @PathVariable("fileName") String fileName, Principal principal) {
        try {
            return storageService.retrieveFileForUsername(fileName, principal.getName())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File name cannot be resolved to valid URL");
        }
    }
}
