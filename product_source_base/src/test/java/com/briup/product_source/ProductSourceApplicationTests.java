package com.briup.product_source;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class ProductSourceApplicationTests {

    @Test
    void contextLoads() {
        String test = UUID.randomUUID().toString().replace("-", "");
        String test1 = UUID.randomUUID().toString();
        System.out.println("test = " + test);
        System.out.println("test1 = " + test1);
    }


}
