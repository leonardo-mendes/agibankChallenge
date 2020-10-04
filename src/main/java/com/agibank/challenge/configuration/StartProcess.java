package com.agibank.challenge.configuration;

import com.agibank.challenge.processor.impl.ChainProcessor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StartProcess {

    @Scheduled(fixedDelay = 500)
    public void start() {
        new ChainProcessor().runProcess().moveForward(List.of());
    }
}
