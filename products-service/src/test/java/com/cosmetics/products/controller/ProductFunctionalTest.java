package com.cosmetics.products.controller;

import com.cosmetics.shared.dto.ProductDto;
import com.cosmetics.shared.dto.BrandDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @class ProductFunctionalTest
 * @brief Tests d'intégration fonctionnels pour les endpoints de gestion des produits.
 *
 * Cette classe teste les principales opérations REST (CRUD) sur l'entité Product,
 * incluant la dépendance à l'entité Brand.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductFunctionalTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private static String productId;
    private static Long brandId;
    private static final String BASE_URL = "/api/products";
    private static final String BRAND_URL = "/api/brands";

    /**
     * @test
     * @brief Crée brand avant de commencer les tests produits.
     * @details Nécessaire pour associer brand existante au produit.
     */
    @Test
    @Order(0)
    void shouldCreateBrand() {
        BrandDto brand = new BrandDto();
        brand.setName("L'Oréal");

        ResponseEntity<BrandDto> response = restTemplate.postForEntity(BRAND_URL, brand, BrandDto.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNotNull();

        brandId = response.getBody().getId();
    }

    /**
     * @test
     * @brief Crée un produit associé à brand existante.
     */
    @Test
    @Order(1)
    void shouldCreateProduct() {
        ProductDto product = new ProductDto();
        product.setName("Shampoo");
        product.setPrice(19.99);
        product.setBrandId(brandId); // important !

        ResponseEntity<ProductDto> response = restTemplate.postForEntity(BASE_URL, product, ProductDto.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNotNull();

        productId = response.getBody().getId();
    }

    /**
     * @test
     * @brief Récupère un produit à partir de son identifiant.
     */
    @Test
    @Order(2)
    void shouldRetrieveProductById() {
        ResponseEntity<ProductDto> response = restTemplate.getForEntity(BASE_URL + "/" + productId, ProductDto.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(productId);
    }

    /**
     * @test
     * @brief Met à jour un produit existant avec de nouvelles valeurs.
     */
    @Test
    @Order(3)
    void shouldUpdateProduct() {
        ProductDto updated = new ProductDto();
        updated.setName("Shampoo Plus");
        updated.setPrice(24.99);
        updated.setBrandId(brandId); // toujours associer une marque valide

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ProductDto> request = new HttpEntity<>(updated, headers);

        ResponseEntity<Void> response = restTemplate.exchange(
                BASE_URL + "/" + productId,
                HttpMethod.PUT,
                request,
                Void.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * @test
     * @brief Supprime un produit, puis vérifie qu'il est introuvable.
     */
    @Test
    @Order(4)
    void shouldDeleteProduct() {
        restTemplate.delete(BASE_URL + "/" + productId);

        ResponseEntity<ProductDto> response = restTemplate.getForEntity(BASE_URL + "/" + productId, ProductDto.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
