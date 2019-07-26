package blayzer.webservice.bussines.service;

import blayzer.webservice.dal.entity.ProductEntity;
import blayzer.webservice.dal.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductEntity getByID(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public ProductEntity addItem(ProductEntity user) {
        return productRepository.saveAndFlush(user);
    }

    @Override
    public ProductEntity editItem(ProductEntity user) {
        return productRepository.saveAndFlush(user);
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
