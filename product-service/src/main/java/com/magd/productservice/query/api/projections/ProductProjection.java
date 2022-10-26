package com.magd.productservice.query.api.projections;

import com.magd.productservice.command.api.data.Product;
import com.magd.productservice.command.api.data.ProductRepository;
import com.magd.productservice.command.api.model.ProductRestModel;
import com.magd.productservice.query.api.queries.GetProductsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductProjection {

    private ProductRepository productRepository;

    public ProductProjection(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @QueryHandler
    public List<ProductRestModel> handle(GetProductsQuery getProductsQuery) {
        List<Product> products =
                productRepository.findAll();
        return products.stream()
                .map(product -> ProductRestModel
                        .builder()
                        .quantity(product.getQuantity())
                        .price(product.getPrice())
                        .name(product.getName())
                        .build())
                .collect(Collectors.toList());
    }


}
