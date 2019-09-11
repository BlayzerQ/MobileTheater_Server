package com.forgegrid.bussines.service;

import com.forgegrid.dal.entity.NewsEntity;

import java.util.List;
import java.util.Optional;

public interface NewsService {

    Optional<NewsEntity> getByID(Long id);

    List<NewsEntity> getAll();
}
