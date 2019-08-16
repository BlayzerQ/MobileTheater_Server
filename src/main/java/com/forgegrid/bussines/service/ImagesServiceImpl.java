package com.forgegrid.bussines.service;

import com.forgegrid.dal.entity.Image;
import com.forgegrid.dal.repository.ImagesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImagesServiceImpl implements ImagesService {

    private final ImagesRepository imagesRepository;

    public ImagesServiceImpl(ImagesRepository imagesRepository) {
        this.imagesRepository = imagesRepository;
    }

    @Override
    public Optional<Image> findImageById(Long id) {
        return imagesRepository.findById(id);
    }
}
