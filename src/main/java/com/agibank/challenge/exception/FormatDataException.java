package com.agibank.challenge.exception;

public class FormatDataException extends RuntimeException {

    public FormatDataException(){
        super("Data format is in invalid.");
    }

    public FormatDataException(String message){
        super(message);
    }

}
