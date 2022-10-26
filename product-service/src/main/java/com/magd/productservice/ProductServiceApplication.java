package com.magd.productservice;

import com.magd.productservice.command.api.exception.ProductServiceEventsErrorHandler;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Autowired
    public void configure(EventProcessingConfigurer conf) {
        conf.registerListenerInvocationErrorHandler(
                "product",
                configuration -> new ProductServiceEventsErrorHandler()
        );
    }

}


