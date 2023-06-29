package com.mca.backendDevTest.controller;

import com.mca.backendDevTest.service.SimilarProductsService;
import com.mca.backendDevTest.service.dto.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
class SimilarProductsControllerIT {

    @Mock
    private SimilarProductsService similarProductsService;

    @InjectMocks
    private SimilarProductsController similarProductsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetSimilarProducts() {
        // Mock data
        Long productId = 123L;
        List<Product> products = Arrays.asList(
                new Product(1L, "Product 1", 2.3, true),
                new Product(2L, "Product 2", 2.3, true),
                new Product(3L, "Product 3", 2.3, true)
        );

        // Mock service response
        when(similarProductsService.getSimilarProductDetails(productId))
                .thenReturn(Mono.just(products));

        // Perform the request
        Mono<List<Product>> result = similarProductsController.getSimilarProducts(productId);

        // Verify the result
        StepVerifier.create(result)
                .expectNext(products)
                .verifyComplete();

        // Verify that the service method was called with the correct parameter
        verify(similarProductsService).getSimilarProductDetails(productId);
        verifyNoMoreInteractions(similarProductsService);
    }
}