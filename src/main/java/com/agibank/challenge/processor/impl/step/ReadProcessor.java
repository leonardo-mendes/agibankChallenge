package com.agibank.challenge.processor.impl.step;

import com.agibank.challenge.domain.Report;
import com.agibank.challenge.exception.ReadFileException;
import com.agibank.challenge.service.DataService;
import com.agibank.challenge.util.FileUtil;
import com.agibank.challenge.util.enums.Extension;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.agibank.challenge.util.enums.Path.INPUT;
import static com.agibank.challenge.util.enums.Splitter.ITEM;

@Slf4j
@RequiredArgsConstructor
public class ReadProcessor extends StepProcessor {

    private final DataService dataService = new DataService();

    @Override
    public List<Report> process(List<Report> reports) {
        return Stream.of(Objects.requireNonNull(new File(INPUT.path).listFiles()))
                .filter(file -> !file.isDirectory())
                .map(this::processLines).collect(Collectors.toList());
    }

    private Report processLines(File file) {
        Report report = Report.builder().error(Boolean.FALSE).fileName(file.getName()).build();
        if (checkExtension(report)) {
            extractLines(file.getName(), report).forEach(line -> dataService.addData(line.split(ITEM.value), report));
        } else {
            report.setError(Boolean.TRUE);
            log.error("Cannot process the {} file.", report.getFileName());
        }
        return report;
    }

    private boolean checkExtension(Report report) {
        return report.getFileName().contains(Extension.INPUT_DAT.value);
    }

    private List<String> extractLines(String fileName, Report report) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(FileUtil.readFile(fileName)));
            if (lines.isEmpty()) {
                report.setError(Boolean.TRUE);
            }
            return lines;
        } catch (IOException e) {
            throw new ReadFileException(e.getMessage());
        }
    }
}
