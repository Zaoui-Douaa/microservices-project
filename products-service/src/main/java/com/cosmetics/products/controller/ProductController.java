package com.cosmetics.products.controller;

import com.cosmetics.products.dto.ProductDto;
import com.cosmetics.products.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable String id) {
        return service.getProductById(id);
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto dto) {
        return service.createProduct(dto);
    }

    @PutMapping("/{id}")
    public ProductDto updateProduct(@PathVariable String id, @RequestBody ProductDto dto) {
        return service.updateProduct(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        service.deleteProduct(id);
    }

    @GetMapping("/{id}/with-brand")
    public ProductDto getProductWithBrand(@PathVariable String id) {
        return service.getProductWithBrand(id);
    }

}