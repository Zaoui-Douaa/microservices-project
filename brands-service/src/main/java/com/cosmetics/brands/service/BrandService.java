package com.cosmetics.brands.service;

import com.cosmetics.brands.dto.BrandDto;
import com.cosmetics.brands.entity.Brand;
import com.cosmetics.brands.mapper.BrandMapper;
import com.cosmetics.brands.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    private final BrandRepository repository;
    private final BrandMapper mapper;

    public BrandService(BrandRepository repository, BrandMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<BrandDto> getAllBrands() {
        return mapper.toDtos(repository.findAll());
    }

    public BrandDto getBrandById(Long id) {
        return repository.findById(id).map(mapper::toDto).orElse(null);
    }

    public BrandDto createBrand(BrandDto dto) {
        Brand saved = repository.save(mapper.toEntity(dto));
        return mapper.toDto(saved);
    }

    public BrandDto updateBrand(Long id, BrandDto dto) {
        Brand existing = repository.findById(id).orElseThrow();
        mapper.updateEntityFromDto(dto, existing);  // Appel de la méthode MapStruct
        return mapper.toDto(repository.save(existing));
    }
    public void deleteBrand(Long id) {
        repository.deleteById(id);
    }
}