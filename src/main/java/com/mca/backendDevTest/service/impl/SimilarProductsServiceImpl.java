package com.mca.backendDevTest.service.impl;

import com.mca.backendDevTest.service.SimilarProductsService;
import com.mca.backendDevTest.service.dto.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

/**
 * Service Implementation for managing
 */
@Service
@RequiredArgsConstructor
public class SimilarProductsServiceImpl implements SimilarProductsService {

    private final Logger log = LoggerFactory.getLogger(SimilarProductsServiceImpl.class);
    private final WebClient webClient;

    @Value("${api.similar.id.uri}")
    private String getsimilarIdsUri;

    @Value("${api.similar.product.detail}")
    private String getProductDetailUri;

    @Override
    @Cacheable(value = "similarProductsCache", key = "#productId")
    public Mono<List<Product>> getSimilarProductDetails(Long productId) {
        log.debug("Get similar Products of productId");

        return getProduct(productId)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found")))
                .flatMap(product -> getSimilarProductIds(productId)
                        .flatMap(this::fetchProducts))
                .cache(
                        r -> Duration.ofSeconds(60),
                        error -> Duration.ZERO,
                        () -> Duration.ZERO);
    }

    @Override
    @Cacheable(value = "similarProductIdsCache", key = "#productId")
    public Mono<List<Long>> getSimilarProductIds(Long productId) {
        log.debug(String.format("Calling getSimilarProductIds(%d)", productId));

        return webClient.get()
                .uri(getsimilarIdsUri, productId)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Long>>() {})
                .cache(
                        r -> Duration.ofSeconds(60),
                        error -> Duration.ZERO,
                        () -> Duration.ZERO);
    }

    @Override
    @Cacheable(value = "productsCache", key = "#id")
    public Mono<Product> getProduct(Long id) {
        log.debug(String.format("Calling getProduct(%d)", id));

        return webClient.get()
                .uri(getProductDetailUri, id)
                .exchangeToMono(response -> {
                    if (response.statusCode().is4xxClientError()) {
                        return Mono.empty();
                    } else {
                        return response.bodyToMono(Product.class);
                    }
                })
                .cache(
                        r -> Duration.ofSeconds(60),
                        error -> Duration.ZERO,
                        () -> Duration.ZERO);

    }

    @Override
    public Mono<List<Product>> fetchProducts(List<Long> productIds) {
        return Flux.fromIterable(productIds)
                .flatMapSequential(this::getProduct)
                .collectList();
    }
}