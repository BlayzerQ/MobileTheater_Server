package blayzer.webservice.service;

import blayzer.webservice.entities.News;
import blayzer.webservice.entities.Product;

import java.util.List;

public interface ProductService {

    Product getByID(Long id);
    Product addItem(Product news);
    Product editItem(Product news);
    void deleteItem(Long id);
    List<Product> getAll();
}
