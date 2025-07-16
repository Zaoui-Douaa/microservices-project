package com.cosmetics.products.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.cosmetics.shared.dto.ProductDto;


@Service
public class ProductEventProducer {

    private final KafkaTemplate<String, ProductDto> kafkaTemplate;

    public ProductEventProducer(KafkaTemplate<String, ProductDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendProduct(ProductDto productDto) {
        kafkaTemplate.send("product-events", productDto);
    }
}
