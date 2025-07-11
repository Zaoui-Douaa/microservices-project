package com.cosmetics.brandsservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ProductEventListener {

    @KafkaListener(topics = "product-events", groupId = "brands-service")
    public void handleProductEvent(String message) {
        System.out.println("Réception d'un événement Kafka : " + message);
        // TODO : Traiter le message (ex: mettre à jour un cache ou base locale)
    }
}