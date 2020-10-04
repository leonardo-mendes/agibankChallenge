package com.agibank.challenge.parser.impl;

import com.agibank.challenge.domain.Salesman;
import com.agibank.challenge.exception.FormatDataException;
import com.agibank.challenge.parser.DataParser;

public class SalesmanParserImpl implements DataParser {

    @Override
    public Salesman parser(String[] data) {
        try{
            return Salesman.builder()
                    .cpf(data[1])
                    .name(data[2])
                    .salary(Double.parseDouble(data[3]))
                    .build();
        } catch (IndexOutOfBoundsException e){
            throw new FormatDataException();
        }
    }
}
