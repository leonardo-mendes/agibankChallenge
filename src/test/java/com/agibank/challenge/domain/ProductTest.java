package com.agibank.challenge.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
public class ProductTest {

    @Test
    public void should_calculate_amount(){
        Product product = Product.builder()
                .itemId("1")
                .quantity(2)
                .value(2.0)
                .build();
        Assertions.assertEquals(4.0, product.getAmount());
    }

}
