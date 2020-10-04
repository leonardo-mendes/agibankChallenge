package com.agibank.challenge.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    private String itemId;
    private Integer quantity;
    private Double value;

    public Double getAmount(){
        return quantity * value;
    }
}
