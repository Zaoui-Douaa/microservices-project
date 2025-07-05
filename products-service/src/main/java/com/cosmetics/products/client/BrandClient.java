package com.cosmetics.products.client;

import com.cosmetics.shared.dto.BrandDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.List;

@FeignClient(
    name = "brands-service",
    fallback = FallBackFeignClient.class
)
public interface BrandClient {

    @GetMapping("/api/brands/{id}")
    BrandDto getBrandById(@PathVariable("id") Long id);
}