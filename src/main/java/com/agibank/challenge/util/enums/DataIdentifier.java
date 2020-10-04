package com.agibank.challenge.util.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DataIdentifier {
    SALESMAN("001"),
    CLIENT("002"),
    ORDER("003");

    public String code;
}
