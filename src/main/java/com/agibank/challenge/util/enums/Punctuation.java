package com.agibank.challenge.util.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Punctuation {
    BLANK(""),
    OPENED_SQUARE_BRACKET("["),
    CLOSED_SQUARE_BRACKET("]");

    public String value;
}
