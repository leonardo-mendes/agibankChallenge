package com.agibank.challenge.configuration;

import com.agibank.challenge.processor.impl.ChainProcessor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@Component
@Profile("!test")
public class StartProcess {

    @PostConstruct
    private void pathWatcher() throws IOException, InterruptedException {
        WatchService watchService
                = FileSystems.getDefault().newWatchService();
        Paths.get(com.agibank.challenge.util.enums.Path.INPUT.path)
                .register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

        WatchKey key;
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                new ChainProcessor().runProcess().moveForward(List.of());
            }
            key.reset();
        }
    }
}
