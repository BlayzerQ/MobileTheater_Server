package com.forgegrid.bussines.service.impl;

import com.forgegrid.bussines.service.StorageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

public class FileSystemStorageService implements StorageService {

    private final Path rootLocation = Paths.get("./upload");

    @Override
    public void saveFileForUsername(MultipartFile file, String username) throws IOException {
        try { // TODO: Consider better place for root directory creation
            Files.createDirectories(rootLocation);
            Files.createDirectory(rootLocation.resolve(username));
        } catch (IOException e) {
            throw new IOException("Could not initialize file storage root location", e);
        }
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, rootLocation.resolve(username).resolve(filename), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    @Override
    public Optional<Resource> retrieveFileForUsername(String fileName, String username) throws MalformedURLException {
        Path resolvedFilePath = rootLocation.resolve(username).resolve(fileName);
        Resource fileAsResource = new UrlResource(resolvedFilePath.toUri());
        if (fileAsResource.exists() || fileAsResource.isReadable()) {
            return Optional.of(fileAsResource);
        }
        return Optional.empty();
    }
}
