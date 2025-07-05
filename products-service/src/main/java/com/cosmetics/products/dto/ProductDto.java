package com.cosmetics.products.dto;

import com.cosmetics.products.mapper.ProductMapper;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String id;
    private String name;
    private Long brandId;
    private Double price;
    private String category;
    private List<String> ingredients;
    private List<String> labels;
    private boolean inStock;

    private BrandDto brand;
    
}