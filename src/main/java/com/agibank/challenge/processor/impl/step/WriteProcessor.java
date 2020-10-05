package com.agibank.challenge.processor.impl.step;

import com.agibank.challenge.domain.Report;
import com.agibank.challenge.exception.WriteFileException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import static com.agibank.challenge.util.enums.Extension.INPUT_DAT;
import static com.agibank.challenge.util.enums.Extension.OUTPUT_DAT;
import static com.agibank.challenge.util.enums.Path.OUTPUT;

public class WriteProcessor extends StepProcessor {

    @Override
    public List<Report> process(List<Report> reports) {
        reports.forEach(report -> {
            if (!report.getError()) {
                try {
                    OutputStream output = new FileOutputStream(sanitizeOutput(report.getFileName()));
                    String result = buildResultLine(report);
                    int control = 1;
                    while (control <= result.length()) {
                        output.write(result.charAt(control - 1));
                        control++;
                    }
                    output.close();
                } catch (IOException e) {
                    throw new WriteFileException(e.getMessage());
                }
            }
        });
        return reports;
    }

    private String sanitizeOutput(String fileName) {
        return OUTPUT.path.concat(
                fileName.replace(INPUT_DAT.value, OUTPUT_DAT.value));
    }

    private String buildResultLine(Report report) {
        return String.format("Salesmen: %s - Clients: %s - Best Sale: %s - Lower Sale: %s",
                report.countSalesmen(),
                report.countClients(),
                report.getHighestSale().getSalesId(),
                report.getLowestSale().getSalesmanName());
    }
}
