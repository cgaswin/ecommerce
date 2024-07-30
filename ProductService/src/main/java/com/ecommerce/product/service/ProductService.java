package com.ecommerce.product.service;

import com.ecommerce.product.model.Product;
import com.ecommerce.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(Long id){
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    public List<Product> getProductsByCategory(String category){
        return productRepository.findProductByCategory(category);
    }

    public Product addProduct(Product product){
        System.out.println(product);
        return productRepository.save(product);
    }

    public Boolean deleteProduct(Long id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            productRepository.delete(product.get());
            return true;
        }
        return false;
    }

    public Product updateProduct(Product newProduct,Long id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            newProduct.setProductId(product.get().getProductId());
            return productRepository.save(newProduct);
        }
        return null;
    }

}
