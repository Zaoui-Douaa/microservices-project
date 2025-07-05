package com.cosmetics.products.client;

import com.cosmetics.shared.dto.BrandDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FallBackFeignClient implements BrandClient {

    
    public BrandDto getBrandById(Long id) {
        return new BrandDto(null, "Marque inconnue", "N/A", "N/A", List.of("INCONNU"));
    }
}