package com.cosmetics.brands.mapper;

import com.cosmetics.brands.dto.BrandDto;
import com.cosmetics.brands.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BrandMapper {

    BrandMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(BrandMapper.class);

    BrandDto toDto(Brand brand);
    Brand toEntity(BrandDto brandDto);

    List<BrandDto> toDtos(List<Brand> brands);
    List<Brand> toEntities(List<BrandDto> dtos);

    // Méthode pour mise à jour d'une entité existante
    void updateEntityFromDto(BrandDto brandDto, @MappingTarget Brand brand);
}