package com.forgegrid.bussines.service;

import com.forgegrid.dal.entity.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Optional<ProductEntity> getByID(Long id);
    ProductEntity addItem(ProductEntity news);
    ProductEntity editItem(ProductEntity news);
    void deleteItem(Long id);
    List<ProductEntity> getAll();
}
