package com.agibank.challenge.processor.impl.step;

import com.agibank.challenge.domain.Report;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.List;

import static com.agibank.challenge.util.enums.Path.*;

@Slf4j
public class MoveProcessor extends StepProcessor {

    @Override
    public List<Report> process(List<Report> reports) {
        reports.forEach(report -> {
            new File(INPUT.path.concat(report.getFileName())).renameTo(new File(resolveOutput(report)));
            log.info("Processed {} file.", report.getFileName());
        });
        return reports;
    }

    private String resolveOutput(Report report){
        return (report.getError())
                ? FAILURE_BACKUP.path.concat(report.getFileName())
                : BACKUP.path.concat(report.getFileName());
    }
}
