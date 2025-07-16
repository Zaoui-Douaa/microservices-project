package com.cosmetics.products.service;

import com.cosmetics.products.client.BrandClient;
import com.cosmetics.products.mapper.ProductMapper;
import com.cosmetics.products.model.Product;
import com.cosmetics.products.repository.ProductRepository;
import com.cosmetics.shared.dto.ProductDto;
import com.cosmetics.shared.dto.BrandDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @Mock
    private ProductMapper mapper;

    @Mock
    private BrandClient brandClient;

    @InjectMocks
    private ProductService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetProductById() {
        Product product = new Product("1", "Test Product", 123L, 99.99,
                "Description", List.of("Red"), List.of("M"), true);

        ProductDto dto = new ProductDto("1", "Test Product", 123L, 99.99,
                "Description", List.of("Red"), List.of("M"), true, null);

        when(repository.findById("1")).thenReturn(Optional.of(product));
        when(mapper.toDto(product)).thenReturn(dto);

        ProductDto result = service.getProductById("1");

        assertNotNull(result);
        assertEquals("Test Product", result.getName());
        verify(repository, times(1)).findById("1");
    }
}
