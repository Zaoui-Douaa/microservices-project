package com.cosmetics.brands.repository;

import com.cosmetics.brands.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}