package com.cosmetics.brands.mapper;

import com.cosmetics.brands.dto.BrandDto;
import com.cosmetics.brands.entity.Brand;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-10T20:21:23+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Ubuntu)"
)
@Component
public class BrandMapperImpl implements BrandMapper {

    @Override
    public BrandDto toDto(Brand brand) {
        if ( brand == null ) {
            return null;
        }

        BrandDto brandDto = new BrandDto();

        brandDto.setId( brand.getId() );
        brandDto.setName( brand.getName() );
        brandDto.setFounder( brand.getFounder() );
        brandDto.setCountry( brand.getCountry() );
        List<String> list = brand.getEthicalValues();
        if ( list != null ) {
            brandDto.setEthicalValues( new ArrayList<String>( list ) );
        }

        return brandDto;
    }

    @Override
    public Brand toEntity(BrandDto brandDto) {
        if ( brandDto == null ) {
            return null;
        }

        Brand brand = new Brand();

        brand.setId( brandDto.getId() );
        brand.setName( brandDto.getName() );
        brand.setFounder( brandDto.getFounder() );
        brand.setCountry( brandDto.getCountry() );
        List<String> list = brandDto.getEthicalValues();
        if ( list != null ) {
            brand.setEthicalValues( new ArrayList<String>( list ) );
        }

        return brand;
    }

    @Override
    public List<BrandDto> toDtos(List<Brand> brands) {
        if ( brands == null ) {
            return null;
        }

        List<BrandDto> list = new ArrayList<BrandDto>( brands.size() );
        for ( Brand brand : brands ) {
            list.add( toDto( brand ) );
        }

        return list;
    }

    @Override
    public List<Brand> toEntities(List<BrandDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Brand> list = new ArrayList<Brand>( dtos.size() );
        for ( BrandDto brandDto : dtos ) {
            list.add( toEntity( brandDto ) );
        }

        return list;
    }

    @Override
    public void updateEntityFromDto(BrandDto brandDto, Brand brand) {
        if ( brandDto == null ) {
            return;
        }

        brand.setId( brandDto.getId() );
        brand.setName( brandDto.getName() );
        brand.setFounder( brandDto.getFounder() );
        brand.setCountry( brandDto.getCountry() );
        if ( brand.getEthicalValues() != null ) {
            List<String> list = brandDto.getEthicalValues();
            if ( list != null ) {
                brand.getEthicalValues().clear();
                brand.getEthicalValues().addAll( list );
            }
            else {
                brand.setEthicalValues( null );
            }
        }
        else {
            List<String> list = brandDto.getEthicalValues();
            if ( list != null ) {
                brand.setEthicalValues( new ArrayList<String>( list ) );
            }
        }
    }
}
