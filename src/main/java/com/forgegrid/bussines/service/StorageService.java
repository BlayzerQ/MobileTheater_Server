package com.forgegrid.bussines.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Optional;

public interface StorageService {

    void saveFileForUsername(MultipartFile file, String username) throws IOException;

    Optional<Resource> retrieveFileForUsername(String fileName, String username) throws MalformedURLException;
}
