package com.cosmetics.products.service;

import com.cosmetics.products.client.BrandClient;
import com.cosmetics.shared.dto.ProductDto;
import com.cosmetics.shared.dto.BrandDto; 
import com.cosmetics.products.mapper.ProductMapper;
import com.cosmetics.products.model.Product;
import com.cosmetics.products.repository.ProductRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Service
@Slf4j
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository repository;
    private final ProductMapper mapper;
    private final BrandClient brandClient;

    public ProductService(ProductRepository repository, ProductMapper mapper, BrandClient brandClient) {
        this.repository = repository;
        this.mapper = mapper;
        this.brandClient = brandClient;
    }

    public List<ProductDto> getAllProducts() {
        return mapper.toDtos(repository.findAll());
    }

    public ProductDto getProductById(String id) {
        return repository.findById(id).map(mapper::toDto).orElse(null);
    }

    public ProductDto createProduct(ProductDto dto) {
        Product saved = repository.save(mapper.toEntity(dto));
        return mapper.toDto(saved);
    }

    public ProductDto updateProduct(String id, ProductDto dto) {
        Product existing = repository.findById(id).orElseThrow();
        mapper.updateEntityFromDto(dto, existing); // ✅
        return mapper.toDto(repository.save(existing));
    }

    public void deleteProduct(String id) {
        repository.deleteById(id);
    }

    @CircuitBreaker(name = "brandService", fallbackMethod = "getDefaultBrand")
    public ProductDto getProductWithBrand(String productId) {

        Product product = repository.findById(productId).orElseThrow();
        
        BrandDto brand = brandClient.getBrandById(product.getBrandId());

        ProductDto dto = mapper.toDto(product);

        dto.setBrand(brand);

        return dto;
    }

    private ProductDto getDefaultBrand(String productId, Throwable t) {
        log.warn("Fallback activé pour brandService - Raison: {}", t.getMessage());

        Product product = repository.findById(productId).orElseThrow();
        ProductDto dto = mapper.toDto(product);
        dto.setBrand(new BrandDto(null, "Marque inconnue", "N/A", "N/A", List.of("INCONNU")));
        return dto;
    }

}