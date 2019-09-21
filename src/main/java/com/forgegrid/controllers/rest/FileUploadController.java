package com.forgegrid.controllers.rest;

import com.forgegrid.bussines.service.StorageService;
import com.forgegrid.validation.annotations.ValidFile;
import com.forgegrid.validation.annotations.ValidFileName;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("/rest/file")
@CrossOrigin("${front.web.url}")
@Validated
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

    @GetMapping("/retrieve/{fileName:.+}")
    public Resource retrieveFile(@ValidFileName @PathVariable("fileName") String fileName, Principal principal) {
        try {
            return storageService.retrieveFileForUsername(fileName, principal.getName())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File name cannot be resolved to valid URL");
        }
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleResourceNotFoundException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        StringBuilder violationMessages = new StringBuilder();
        for (ConstraintViolation<?> violation : violations) {
            violationMessages.append(violation.getMessage()).append("\n");
        }
        return violationMessages.toString();
    }
}
