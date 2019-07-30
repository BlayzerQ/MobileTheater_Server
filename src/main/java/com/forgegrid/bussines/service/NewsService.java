package com.forgegrid.bussines.service;

import com.forgegrid.dal.entity.NewsEntity;

import java.util.List;

public interface NewsService {

    NewsEntity getByID(Long id);
    NewsEntity addItem(NewsEntity news);
    NewsEntity editItem(NewsEntity news);
    void deleteItem(Long id);
    List<NewsEntity> getAll();
}
