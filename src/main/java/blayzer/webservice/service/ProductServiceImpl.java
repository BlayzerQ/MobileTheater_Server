package blayzer.webservice.service;

import blayzer.webservice.entities.News;
import blayzer.webservice.entities.Product;
import blayzer.webservice.repository.NewsRepository;
import blayzer.webservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product getByID(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public Product addItem(Product user) {
        return productRepository.saveAndFlush(user);
    }

    @Override
    public Product editItem(Product user) {
        return productRepository.saveAndFlush(user);
    }

    @Override
    public void deleteItem(Long id) {
        productRepository.delete(id);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

}
