package com.cosmetics.products.dto;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String id;
    private String name;
    private String brandId;
    private Double price;
    private String category;
    private List<String> ingredients;
    private List<String> labels;
    private boolean inStock;
}