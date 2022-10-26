package com.magd.productservice.query.api.controller;


import com.magd.productservice.command.api.model.ProductRestModel;
import com.magd.productservice.query.api.queries.GetProductsQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductQueryController {

    private final QueryGateway queryGateway;

    public ProductQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping
    public List<ProductRestModel> getAllProducts() {
        GetProductsQuery getProductsQuery =
                new GetProductsQuery();

        return queryGateway.query(getProductsQuery,
                        ResponseTypes.multipleInstancesOf(ProductRestModel.class))
                .join();
    }
}
