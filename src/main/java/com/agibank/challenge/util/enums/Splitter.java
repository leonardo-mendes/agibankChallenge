package com.agibank.challenge.util.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Splitter {
    ITEM("ç"),
    HYPHEN("-"),
    COMMA(",");

    public String value;
}
