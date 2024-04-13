package com.example.sales.product;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    @Autowired
    public ProductService(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }
    public void addNewProduct(Product product) {
        Optional<Product> productOptional = productRepository.findProductByReference(product.getRef());
        if(productOptional.isPresent()){
            throw new IllegalStateException("Reference Taken");
        }
        productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        boolean exists = productRepository.existsById(productId);
        if(!exists){
            throw new IllegalStateException("Product with Id "+ productId + " does not exists");
        }
        productRepository.deleteById(productId);
    }
    @Transactional
    public void updateProduct( Product pProduct,Long productId) {
        String desc = pProduct.getDesc();
        String ref = pProduct.getRef();
        String type = pProduct.getType();
        double price = pProduct.getPrice();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalStateException("Product with Id " + productId + " does not exists"));
        if(desc != null && !Objects.equals(product.getDesc(),desc)
        ){
            product.setDesc(desc);
        }
        if(ref != null && !Objects.equals(product.getRef(),ref)
        ){
            Optional<Product> optionalProduct = productRepository.findProductByReference(ref);
            if(optionalProduct.isPresent()){
                throw new IllegalStateException("Another product already has this reference");
            }
            product.setRef(ref);
        }
        if(type != null && !Objects.equals(product.getType(),type)
        ){
            product.setType(type);
        }
        if(price != 0 && !Objects.equals(product.getPrice(), price)
        ){
            product.setPrice(price);
        }
//        productRepository.save(product);
    }

    public List<ProductDDTO> getProductsDD() {
        List<Product> products = productRepository.findAll();
        List<ProductDDTO> productsDD =  new ArrayList<>();
        for (Product product : products) {
            productsDD.add(new ProductDDTO(product.getId(),  product.getRef()));
        }
        return productsDD;
    }


}