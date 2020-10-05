package com.agibank.challenge.configuration;

import com.agibank.challenge.processor.impl.ChainProcessor;
import com.agibank.challenge.util.enums.Path;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@Profile("!test")
public class StartProcess {

    @PostConstruct
    private void pathWatcher() throws IOException, InterruptedException {
        createResources();
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

    public static void createResources() {
        Arrays.stream(Path.values()).forEach(source -> {
            boolean result = new File(source.path).mkdir();
            if(result){
                log.info("Created {} package.", source.path);
            }
        });
    }
}
