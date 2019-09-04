package com.forgegrid.controllers.rest;

import com.forgegrid.bussines.service.StorageService;
import com.forgegrid.validation.annotations.ValidFile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController("/file")
public class FileUploadController {

    private final StorageService storageService;

    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/upload")
    public void uploadFile(@ValidFile @RequestParam("file") MultipartFile file) {
        try {
            storageService.saveFile(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INSUFFICIENT_STORAGE, "IO error happened while saving file");
        }
    }
}
