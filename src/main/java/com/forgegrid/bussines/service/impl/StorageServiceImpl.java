package com.forgegrid.bussines.service.impl;

import com.forgegrid.bussines.service.StorageService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class StorageServiceImpl implements StorageService {

    private final Path rootLocation = Paths.get("./upload");

    @Override
    public void saveFile(MultipartFile file) throws IOException {
        try { // TODO: Consider better place for root directory creation
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new IOException("Could not initialize file storage root location", e);
        }
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, rootLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
