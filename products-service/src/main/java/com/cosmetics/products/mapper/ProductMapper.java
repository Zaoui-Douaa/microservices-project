package com.cosmetics.products.mapper;

import com.cosmetics.shared.dto.ProductDto;
import com.cosmetics.products.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    Product toEntity(ProductDto productDto);
    
    @Mapping(target = "brand", ignore = true)
    ProductDto toDto(Product product);

    // Méthode pour convertir une liste d'entités en liste de DTOs
    @Mapping(target = "brand", ignore = true)
    List<ProductDto> toDtos(List<Product> products);

    // Méthode pour mettre à jour une entité existante
    void updateEntityFromDto(ProductDto dto, @MappingTarget Product entity);
    
}