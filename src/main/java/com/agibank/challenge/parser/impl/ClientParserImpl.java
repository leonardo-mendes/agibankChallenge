package com.agibank.challenge.parser.impl;

import com.agibank.challenge.domain.Client;
import com.agibank.challenge.exception.FormatDataException;
import com.agibank.challenge.parser.DataParser;

public class ClientParserImpl implements DataParser {

    @Override
    public Client parser(String[] data) {
        try{
            return Client.builder()
                    .cnpj(data[1])
                    .name(data[2])
                    .business(data[3])
                    .build();
        } catch (IndexOutOfBoundsException e){
            throw new FormatDataException();
        }
    }
}
