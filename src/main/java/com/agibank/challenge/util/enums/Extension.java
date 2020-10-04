package com.agibank.challenge.util.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Extension {
    INPUT_DAT(".dat"),
    ERROR_OUTPUT_DAT(".error.dat"),
    OUTPUT_DAT(".done.dat");

    public String value;
}
