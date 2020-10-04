package com.agibank.challenge.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class OrderTest {

    @Test
    public void should_calculate_products_amount(){
        Order order = Order.builder()
                .salesmanName("user")
                .salesId("1")
                .products(List.of(
                        Product.builder()
                                .itemId("1")
                                .quantity(2)
                                .value(2.0)
                                .build(),
                        Product.builder()
                                .itemId("2")
                                .quantity(2)
                                .value(5.0)
                                .build()
                ))
                .build();
        Assertions.assertEquals(14.0, order.getProductsAmount());
    }

}