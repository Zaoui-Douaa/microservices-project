package com.cosmetics.brands.kafka;

import com.cosmetics.shared.dto.ProductDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProductEventListener {

    private static final Logger logger = LoggerFactory.getLogger(ProductEventListener.class);

    @KafkaListener(topics = "product-events", containerFactory = "kafkaListenerContainerFactory")
    public void handleProductEvent() {
                logger.info("Produit reçu via Kafka - ID: {}, Nom: {}");

    }


}
