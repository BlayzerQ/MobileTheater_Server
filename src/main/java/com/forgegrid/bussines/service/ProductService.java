package com.forgegrid.bussines.service;

import com.forgegrid.dal.entity.ProductEntity;

import java.util.List;

public interface ProductService {

    ProductEntity getByID(Long id);
    ProductEntity addItem(ProductEntity news);
    ProductEntity editItem(ProductEntity news);
    void deleteItem(Long id);
    List<ProductEntity> getAll();
}
