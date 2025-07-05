package com.cosmetics.products.service;

import com.cosmetics.products.client.BrandClient;
import com.cosmetics.products.dto.ProductDto;
import com.cosmetics.products.dto.BrandDto; 
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
        logger.info("=== DÉBUT getProductWithBrand pour productId: {} ===", productId);
        
        try {
            // 1. Récupération du produit
            logger.info("Étape 1: Recherche du produit...");
            Product product = repository.findById(productId)
                .orElseThrow(() -> {
                    logger.error("Produit non trouvé avec l'ID: {}", productId);
                    return new RuntimeException("Produit non trouvé avec l'ID : " + productId);
                });
            
            logger.info("Produit trouvé - Nom: {}, BrandId: {}", product.getName(), product.getBrandId());

            // 2. Vérification du brandId
            if (product.getBrandId() == null) {
                logger.error("BrandId est null pour le produit: {}", productId);
                throw new RuntimeException("Ce produit n'a pas de marque associée");
            }

            // 3. Appel FeignClient
            logger.info("Étape 2: Appel FeignClient pour brandId: {}", product.getBrandId());
            BrandDto brand = brandClient.getBrandById(product.getBrandId());
            logger.info("Marque récupérée avec succès: {}", brand != null ? brand.getName() : "null");
            
            // 4. Création du DTO
            logger.info("Étape 3: Création du ProductDto...");
            ProductDto dto = mapper.toDto(product);
            dto.setBrand(brand);
            
            logger.info("=== FIN getProductWithBrand - SUCCÈS ===");
            return dto;
            
        } catch (Exception e) {
            logger.error("=== ERREUR dans getProductWithBrand ===");
            logger.error("Type d'exception: {}", e.getClass().getSimpleName());
            logger.error("Message: {}", e.getMessage());
            logger.error("Stack trace: ", e);
            
            // Re-throw l'exception pour que Spring Boot génère une réponse d'erreur
            throw new RuntimeException("Erreur lors de la récupération du produit avec marque: " + e.getMessage(), e);
        }
    }

    private ProductDto getDefaultBrand(String productId, Throwable t) {
        log.warn("Fallback activé pour brandService - Raison: {}", t.getMessage());

        Product product = repository.findById(productId).orElseThrow();
        ProductDto dto = mapper.toDto(product);
        dto.setBrand(new BrandDto(null, "Marque inconnue", "N/A", "N/A", List.of("INCONNU")));
        return dto;
    }

}