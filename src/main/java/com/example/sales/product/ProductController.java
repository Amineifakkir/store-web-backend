package com.example.sales.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(
        origins = {
                "*"
        },
        methods = {
                RequestMethod.OPTIONS,
                RequestMethod.GET,
                RequestMethod.PUT,
                RequestMethod.DELETE,
                RequestMethod.POST
        })
@RestController
@RequestMapping(path="api/v1/product")
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping(path = "DD")
    public List<ProductDDTO> getProductsDD(){
        return productService.getProductsDD();
    }
    @GetMapping
    public List<Product> getProducts(){
        return productService.getProducts();
    }
    @PostMapping
    public List<Product> registerNewProduct(@RequestBody Product product){
        productService.addNewProduct(product);
        return productService.getProducts();
    }
    @DeleteMapping(path = "{productId}")
    public List<Product> deleteProduct(@PathVariable("productId") Long productId){
        productService.deleteProduct(productId);
        return productService.getProducts();
    }
    @PutMapping(path = "{productId}")
    public List<Product> updateProduct(
            @PathVariable("productId") Long productId,
            @RequestBody Product product){
        productService.updateProduct(product,productId);
        return productService.getProducts();
    }
}
