package com.forgegrid.bussines.service;

import com.forgegrid.dal.entity.Image;
import com.forgegrid.dal.repository.ImagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImagesServiceImpl implements ImagesService {

    private final ImagesRepository imagesRepository;

    @Override
    public Optional<Image> findImageById(Long id) {
        return imagesRepository.findById(id);
    }
}
