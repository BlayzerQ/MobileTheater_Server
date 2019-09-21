package com.forgegrid.bussines.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {

    void saveFileForUsername(MultipartFile file, String username) throws IOException;
}
