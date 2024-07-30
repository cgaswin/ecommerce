package com.ecommerce.product.controller;

import com.ecommerce.product.model.Product;
import com.ecommerce.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        Product product = productService.getProductById(id);
        if(product==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Product>(product,HttpStatus.OK);
    }

    @GetMapping({"/category/{category}"})
    public ResponseEntity<List<Product>> getProductByCategory(@PathVariable String category){
        List<Product> products = productService.getProductsByCategory(category);
        if(products==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> addProduct( @RequestBody Product product){
        System.out.println(product);
        Product newProduct = productService.addProduct(product);
        System.out.println(newProduct);
        return new ResponseEntity<Product>(newProduct,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product newProduct,@PathVariable Long id){
        Product product = productService.updateProduct(newProduct,id);
        if(product==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        Boolean isDeleted = productService.deleteProduct(id);
        if(isDeleted){
            return new ResponseEntity<>("Product deleted",HttpStatus.OK);
        }
        return new ResponseEntity<>("Product not found",HttpStatus.NOT_FOUND);
    }
}
