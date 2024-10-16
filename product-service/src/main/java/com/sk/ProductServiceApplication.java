package com.sk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ProductServiceApplication {

    public static void main(String[] args) {

        SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder(ProductServiceApplication.class);
        springApplicationBuilder.web(WebApplicationType.SERVLET);
        springApplicationBuilder.application().run();

        //SpringApplication.run(ProductServiceApplication.class, args);
    }

}