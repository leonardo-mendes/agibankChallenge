package com.agibank.challenge.processor.impl;

import com.agibank.challenge.processor.impl.step.*;
import org.springframework.stereotype.Component;

@Component
public class ChainProcessor {

    public StepProcessor runProcess(){
        ReadProcessor readProcessor = new ReadProcessor();
        ValidationProcessor validateProcessor = new ValidationProcessor();
        WriteProcessor writeProcessor = new WriteProcessor();
        MoveProcessor moveProcessor = new MoveProcessor();

        readProcessor.setNextProcessor(validateProcessor);
        validateProcessor.setNextProcessor(writeProcessor);
        writeProcessor.setNextProcessor(moveProcessor);
        return readProcessor;
    }
}
