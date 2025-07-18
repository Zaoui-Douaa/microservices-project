package com.cosmetics.products.repository;

import com.cosmetics.products.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}