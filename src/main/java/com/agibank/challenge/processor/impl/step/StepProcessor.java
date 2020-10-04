package com.agibank.challenge.processor.impl.step;

import com.agibank.challenge.domain.Report;
import com.agibank.challenge.processor.Processor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public abstract class StepProcessor implements Processor {

    @Setter
    private StepProcessor nextProcessor;

    protected abstract List<Report> process(List<Report> report);

    public List<Report> moveForward(List<Report> reports){
        List<Report> reportsResult = process(reports);
        if (nextProcessor != null) {
            log.debug("Calling next processor: {}", nextProcessor.getClass().getSimpleName());
            nextProcessor.moveForward(reportsResult);
        }
        return reportsResult;
    }
}
