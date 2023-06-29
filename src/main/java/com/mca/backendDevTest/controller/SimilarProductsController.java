package com.mca.backendDevTest.controller;

import com.mca.backendDevTest.service.SimilarProductsService;
import com.mca.backendDevTest.service.dto.Product;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@AllArgsConstructor
public class SimilarProductsController {

    private final Logger log = LoggerFactory.getLogger(SimilarProductsController.class);
    private SimilarProductsService similarProductsService;

    /**
     * {GET /products}
     *
     * @param productId
     * @return the List of similar products
     */
    @GetMapping("/product/{productId}/similar")
    public Mono<List<Product>> getSimilarProducts(@PathVariable Long productId){
        log.debug("REST request to get similar products");

        Mono<List<Product>> similarProductDetails = similarProductsService.getSimilarProductDetails(productId);
        return similarProductDetails;
    }

}
