package com.cosmetics.products.client;

import com.cosmetics.products.dto.BrandDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
    name = "brands-service"
    //url = "http://brands-service:8082"
)
public interface BrandClient {

    @GetMapping("/api/brands")
    List<BrandDto> getAllBrands();

    @GetMapping("/api/brands/{id}")
    BrandDto getBrandById(@PathVariable("id") Long id);
}