package com.forgegrid.bussines.service;

import com.forgegrid.dal.entity.NewsEntity;
import com.forgegrid.dal.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Override
    public Optional<NewsEntity> getByID(Long id) {
        return newsRepository.findById(id);
    }

    @Override
    public NewsEntity addItem(NewsEntity user) {
        return newsRepository.saveAndFlush(user);
    }

    @Override
    public NewsEntity editItem(NewsEntity user) {
        return newsRepository.saveAndFlush(user);
    }

    @Override
    public void deleteItem(Long id) {
        newsRepository.deleteById(id);
    }

    @Override
    public List<NewsEntity> getAll() {
        return newsRepository.findAll();
    }

}
