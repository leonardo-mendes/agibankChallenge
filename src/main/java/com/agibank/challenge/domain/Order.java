package com.agibank.challenge.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    private String salesId;
    private String salesmanName;
    private List<Product> products;

    public Double getProductsAmount() {
        return products.stream().mapToDouble(Product::getAmount).sum();
    }
}
