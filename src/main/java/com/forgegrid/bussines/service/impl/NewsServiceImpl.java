package com.forgegrid.bussines.service.impl;

import com.forgegrid.bussines.service.NewsService;
import com.forgegrid.dal.entity.NewsEntity;
import com.forgegrid.dal.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    @Override
    public Optional<NewsEntity> getByID(Long id) {
        return newsRepository.findById(id);
    }

    @Override
    public List<NewsEntity> getAll() {
        return newsRepository.findAll();
    }

}
