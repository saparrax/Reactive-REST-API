package com.mca.backendDevTest.service;

import com.mca.backendDevTest.service.dto.Product;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Service Interface for managing
 */
public interface SimilarProductsService {

    /**
     *
     * @param productId
     * @return the list of similar products
     */
    Mono<List<Product>> getSimilarProductDetails(Long productId);

    /**
     *
     * @param productId
     * @return list of ids similar to the productId
     */
    Mono<List<Long>> getSimilarProductIds(Long productId);

    /**
     *
     * @param id
     * @return product detail
     */
    Mono<Product> getProduct(Long id);

    /**
     *
     * @param userIds
     * @return list of products details
     */
    public Mono<List<Product>> fetchProducts(List<Long> userIds);

}
