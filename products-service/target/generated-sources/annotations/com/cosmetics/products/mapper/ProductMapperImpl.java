package com.cosmetics.products.mapper;

import com.cosmetics.products.dto.ProductDto;
import com.cosmetics.products.model.Product;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-28T16:38:38+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Ubuntu)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toEntity(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( productDto.getId() );
        product.setName( productDto.getName() );
        product.setBrandId( productDto.getBrandId() );
        product.setPrice( productDto.getPrice() );
        product.setCategory( productDto.getCategory() );
        List<String> list = productDto.getIngredients();
        if ( list != null ) {
            product.setIngredients( new ArrayList<String>( list ) );
        }
        List<String> list1 = productDto.getLabels();
        if ( list1 != null ) {
            product.setLabels( new ArrayList<String>( list1 ) );
        }
        product.setInStock( productDto.isInStock() );

        return product;
    }

    @Override
    public ProductDto toDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setId( product.getId() );
        productDto.setName( product.getName() );
        productDto.setBrandId( product.getBrandId() );
        productDto.setPrice( product.getPrice() );
        productDto.setCategory( product.getCategory() );
        List<String> list = product.getIngredients();
        if ( list != null ) {
            productDto.setIngredients( new ArrayList<String>( list ) );
        }
        List<String> list1 = product.getLabels();
        if ( list1 != null ) {
            productDto.setLabels( new ArrayList<String>( list1 ) );
        }
        productDto.setInStock( product.isInStock() );

        return productDto;
    }

    @Override
    public List<ProductDto> toDtos(List<Product> products) {
        if ( products == null ) {
            return null;
        }

        List<ProductDto> list = new ArrayList<ProductDto>( products.size() );
        for ( Product product : products ) {
            list.add( toDto( product ) );
        }

        return list;
    }

    @Override
    public void updateEntityFromDto(ProductDto dto, Product entity) {
        if ( dto == null ) {
            return;
        }

        entity.setId( dto.getId() );
        entity.setName( dto.getName() );
        entity.setBrandId( dto.getBrandId() );
        entity.setPrice( dto.getPrice() );
        entity.setCategory( dto.getCategory() );
        if ( entity.getIngredients() != null ) {
            List<String> list = dto.getIngredients();
            if ( list != null ) {
                entity.getIngredients().clear();
                entity.getIngredients().addAll( list );
            }
            else {
                entity.setIngredients( null );
            }
        }
        else {
            List<String> list = dto.getIngredients();
            if ( list != null ) {
                entity.setIngredients( new ArrayList<String>( list ) );
            }
        }
        if ( entity.getLabels() != null ) {
            List<String> list1 = dto.getLabels();
            if ( list1 != null ) {
                entity.getLabels().clear();
                entity.getLabels().addAll( list1 );
            }
            else {
                entity.setLabels( null );
            }
        }
        else {
            List<String> list1 = dto.getLabels();
            if ( list1 != null ) {
                entity.setLabels( new ArrayList<String>( list1 ) );
            }
        }
        entity.setInStock( dto.isInStock() );
    }
}
