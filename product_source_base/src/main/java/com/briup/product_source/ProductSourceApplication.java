package com.briup.product_source;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Hlmove
 */
@SpringBootApplication
@MapperScan("com.briup.product_source.dao")
public class ProductSourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductSourceApplication.class, args);
    }

}
