package com.agibank.challenge.util.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Path {
    INPUT("data/in/"),
    BACKUP("data/backup/"),
    FAILURE_BACKUP("data/backup/failure/"),
    OUTPUT("data/out/");

    public String path;
}
