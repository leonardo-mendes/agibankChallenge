package com.agibank.challenge.parser;

public interface DataParser<T> {

    T parser(String[] data);
}
