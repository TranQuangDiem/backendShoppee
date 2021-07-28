package source.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import source.entity.Product;
import source.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public void save(Product product){
        productRepository.save(product);
    }
}
