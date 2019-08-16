package com.forgegrid.bussines.service;

import com.forgegrid.dal.entity.Image;

import java.util.Optional;

public interface ImagesService {
    Optional<Image> findImageById(Long id);
}
