package com.cosmetics.products.controller;

import com.cosmetics.shared.dto.ProductDto;
import com.cosmetics.products.service.ProductService;
import org.springframework.web.bind.annotation.*;
import com.cosmetics.products.kafka.ProductEventProducer;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;   
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;
    private final ProductEventProducer eventProducer;

    public ProductController(ProductService service, ProductEventProducer eventProducer) {
        this.service = service;
        this.eventProducer = eventProducer;
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

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestParam String message) {
        eventProducer.sendMessage(message);
        return ResponseEntity.ok("Message envoyé via Kafka !");
    }

    @GetMapping("/hello")
    public String hello() {
        return "Bonjour !";
    }
    /*
    @PostMapping("/send-message")   // send message
    public ResponseEntity<String> sendMessage(@RequestBody ProductDto productDTO) {
        eventProducer.sendProduct(productDTO);
        return ResponseEntity.ok("Employé envoyé via Kafka !");
    }
    // 🟡 Send DTO (objet complet)
    @PostMapping("/send-product")
    public ResponseEntity<String> sendProduct(@RequestBody ProductDto productDTO) {
        eventProducer.sendProduct(productDTO);
        return ResponseEntity.ok("Product envoyé !");
    }*/
/*
    @PostMapping("/kafka")
    public ResponseEntity<ProductDto> createProductKafka(@RequestBody ProductDto productDto) throws JsonProcessingException {
        ProductDto createdProduct = service.createProduct(productDto);

        // Convertir l'objet en JSON
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(createdProduct);

        // Envoyer l'événement Kafka
        eventProducer.sendProductCreatedEvent(json);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }
*/

}