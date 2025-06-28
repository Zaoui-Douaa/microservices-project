package com.cosmetics.brands.entity;

import com.cosmetics.brands.converter.StringToListConverter;
import lombok.*;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "brands")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String founder;
    private String country;

    @Column(name = "ethical_values")
    @Convert(converter = StringToListConverter.class)
    private List<String> ethicalValues;
}