package com.cosmetics.brands.controller;

import com.cosmetics.brands.dto.BrandDto;
import com.cosmetics.brands.service.BrandService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

    private final BrandService service;

    public BrandController(BrandService service) {
        this.service = service;
    }

    @GetMapping
    public List<BrandDto> getAllBrands() {
        return service.getAllBrands();
    }

    @GetMapping("/{id}")
    public BrandDto getBrand(@PathVariable Long id) {
        return service.getBrandById(id);
    }

    @PostMapping
    public BrandDto createBrand(@RequestBody BrandDto dto) {
        return service.createBrand(dto);
    }

    @PutMapping("/{id}")
    public BrandDto updateBrand(@PathVariable Long id, @RequestBody BrandDto dto) {
        return service.updateBrand(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteBrand(@PathVariable Long id) {
        service.deleteBrand(id);
    }
}