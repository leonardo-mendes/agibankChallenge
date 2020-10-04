package com.agibank.challenge.parser.impl;

import com.agibank.challenge.domain.Order;
import com.agibank.challenge.domain.Product;
import com.agibank.challenge.exception.FormatDataException;
import com.agibank.challenge.parser.DataParser;
import com.agibank.challenge.util.enums.Splitter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.agibank.challenge.util.enums.Punctuation.*;
import static com.agibank.challenge.util.enums.Splitter.HYPHEN;

public class OrderParserImpl implements DataParser {

    @Override
    public Order parser(String[] data) {
        try{
            return Order.builder()
                    .salesId(data[1])
                    .products(retrieveProducts(data[2]))
                    .salesmanName(data[3])
                    .build();
        } catch (IndexOutOfBoundsException e){
            throw new FormatDataException();
        }
    }

    private List<Product> retrieveProducts(String products) {
        String[] list = sanitizeString(products);
        return Arrays.stream(list)
                .map(line -> {
                    String[] product = line.split(HYPHEN.value);
                    return Product.builder()
                            .itemId(product[0])
                            .quantity(Integer.parseInt(product[1]))
                            .value(Double.parseDouble(product[2]))
                            .build();
                }).collect(Collectors.toList());
    }

    private String[] sanitizeString(String data){
        return data.replace(OPENED_SQUARE_BRACKET.value, BLANK.value)
                .replace(CLOSED_SQUARE_BRACKET.value, BLANK.value)
                .split(Splitter.COMMA.value);
    }

}
