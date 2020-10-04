package com.agibank.challenge.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Salesman {
    private String cpf;
    private String name;
    private Double salary;
}
