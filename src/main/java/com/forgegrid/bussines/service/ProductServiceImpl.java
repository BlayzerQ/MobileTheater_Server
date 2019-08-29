package com.forgegrid.bussines.service;

import com.forgegrid.dal.entity.ProductEntity;
import com.forgegrid.dal.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Optional<ProductEntity> getByID(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public ProductEntity addItem(ProductEntity user) {
        return productRepository.save(user);
    }

    @Override
    public ProductEntity editItem(ProductEntity user) {
        return productRepository.save(user);
    }

    @Override
    public void deleteItem(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductEntity> getAll() {
        return productRepository.findAll();
    }

}
