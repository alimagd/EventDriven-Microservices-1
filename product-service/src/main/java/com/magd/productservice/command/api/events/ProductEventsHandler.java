package com.magd.productservice.command.api.events;

import com.magd.productservice.command.api.data.Product;
import com.magd.productservice.command.api.data.ProductRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
@ProcessingGroup("product")
public class ProductEventsHandler {

    private final ProductRepository productRepository;

    public ProductEventsHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) throws Exception {

        Product product = new Product();
        BeanUtils.copyProperties(event, product);
        productRepository.save(product);
        throw new Exception("Exception Occurred");
    }
    @ExceptionHandler
    public void handler(Exception exception) throws Exception{
        throw exception;
    }
}
