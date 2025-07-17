package com.cosmetics.products.controller;

import com.cosmetics.products.service.ProductService;
import com.cosmetics.shared.dto.ProductDto;
import com.cosmetics.products.kafka.ProductEventProducer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.http.MediaType;
import java.util.List;
import java.util.Arrays;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


/**
 * @file ProductControllerIntegrationTest.java
 * @brief Tests d'intégration pour le contrôleur ProductController.
 */
@WebMvcTest(controllers = ProductController.class)
class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService service;

    @MockBean
    private ProductEventProducer eventProducer;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * @test Vérifie que la liste de tous les produits est correctement retournée.
     *       Simule deux produits et attend une réponse JSON contenant ces produits.
     */
    @Test
    void shouldReturnAllProducts() throws Exception {
        ProductDto product1 = new ProductDto();
        product1.setId("1");
        product1.setName("Shampoo");

        ProductDto product2 = new ProductDto();
        product2.setId("2");
        product2.setName("Vernis");

        List<ProductDto> listProduct = Arrays.asList(product1, product2);

        when(service.getAllProducts()).thenReturn(listProduct);

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Shampoo"));
    }

    /**
     * @test Vérifie que le produit est correctement retourné par ID.
     *       Simule la récupération d’un produit avec l’ID "1".
     */
    @Test
    void shouldReturnProductById() throws Exception {
        ProductDto p = new ProductDto();
        p.setId("1");
        p.setName("Shampoo");

        when(service.getProductById("1")).thenReturn(p);

        mockMvc.perform(get("/api/products/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Shampoo"));
    }

    /**
     * @test Vérifie que la création d’un produit fonctionne.
     *       Envoie un produit en JSON et attend une réponse avec les données du produit créé.
     */
    @Test
    void shouldCreateProduct() throws Exception {
        ProductDto p = new ProductDto();
        p.setId("1");
        p.setName("Shampoo");

        when(service.createProduct(any(ProductDto.class))).thenReturn(p);

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(p)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value("1"))
            .andExpect(jsonPath("$.name").value("Shampoo"));
    }

    /**
     * @test Vérifie que la mise à jour d’un produit fonctionne.
     *       Simule une mise à jour de produit avec l’ID "1".
     */
    @Test
    void shouldUpdateProduct() throws Exception {
        ProductDto p = new ProductDto();
        p.setId("1");
        p.setName("Shampoo Updated");

        when(service.updateProduct(eq("1"), any(ProductDto.class))).thenReturn(p);

        mockMvc.perform(put("/api/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(p)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Shampoo Updated"));
    }

    /**
     * @test Vérifie que la suppression d’un produit par ID fonctionne.
     *       Vérifie aussi que la méthode service.deleteProduct est appelée.
     */
    @Test
    void shouldDeleteProduct() throws Exception {
        doNothing().when(service).deleteProduct("1");

        mockMvc.perform(delete("/api/products/1"))
            .andExpect(status().isOk());

        verify(service, times(1)).deleteProduct("1");
    }

    /**
     * @test Vérifie que le produit avec la marque est retourné.
     *       Simule un appel à getProductWithBrand.
     */
    @Test
    void shouldReturnProductWithBrand() throws Exception {
        ProductDto p = new ProductDto();
        p.setId("1");
        p.setName("Shampoo");
        p.setBrand(null); 

        when(service.getProductWithBrand("1")).thenReturn(p);

        mockMvc.perform(get("/api/products/1/with-brand"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value("1"))
            .andExpect(jsonPath("$.name").value("Shampoo"));
    }

    /**
     * @test Vérifie que l’envoi d’un produit via Kafka fonctionne.
     *       Simule l’appel au ProductEventProducer et vérifie la réponse textuelle.
     */
    @Test
    void shouldSendProductViaKafka() throws Exception {
        ProductDto p = new ProductDto();
        p.setId("1");
        p.setName("Shampoo");

        doNothing().when(eventProducer).sendProduct(any(ProductDto.class));

        mockMvc.perform(post("/api/products/send-product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(p)))
            .andExpect(status().isOk())
            .andExpect(content().string("Produit envoyé via Kafka !"));

        verify(eventProducer, times(1)).sendProduct(any(ProductDto.class));
    }
}
