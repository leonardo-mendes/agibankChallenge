package com.agibank.challenge.util.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Path {
    INPUT("src/main/resources/data/in/"),
    BACKUP("src/main/resources/data/backup/"),
    FAILURE_BACKUP("src/main/resources/data/failure/"),
    OUTPUT("src/main/resources/data/out/");

    public String path;
}
