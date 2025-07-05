package com.cosmetics.shared.dto;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandDto {
    private Long id;
    private String name;
    private String founder;
    private String country;
    private List<String> ethicalValues;
}