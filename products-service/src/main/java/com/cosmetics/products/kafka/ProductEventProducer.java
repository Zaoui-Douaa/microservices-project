package com.cosmetics.products.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.cosmetics.shared.dto.ProductDto;
import org.springframework.beans.factory.annotation.Value;

@Service
public class ProductEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.topic.name}")
    private String topicName;

    public ProductEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        kafkaTemplate.send("product-events", message); // ✅ OK avec Object
    }

   /* public void sendProduct(ProductDto productDTO) {
        kafkaTemplate.send(topicName, productDTO); // ✅ OK avec Object
    }*/

    public void sendProductCreatedEvent(String productJson) {
        kafkaTemplate.send("product-events", productJson);
    }
}