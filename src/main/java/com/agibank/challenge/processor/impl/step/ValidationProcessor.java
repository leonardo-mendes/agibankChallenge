package com.agibank.challenge.processor.impl.step;

import com.agibank.challenge.domain.Report;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ValidationProcessor extends StepProcessor {

    @Override
    public List<Report> process(List<Report> reports) {
        reports.forEach(report -> report.getOrders().forEach(order -> {
            if (!report.getError()) {
                validate(report, order);
            }
        }));
        return reports;
    }

    private void validate(Report report, com.agibank.challenge.domain.Order order) {
        if (!checkSalesman(report, order.getSalesmanName())) {
            report.setError(Boolean.TRUE);
        }
    }

    private boolean checkSalesman(Report report, String salesmanName) {
        return report.getSalesmen().stream().anyMatch(salesman -> salesman.getName().equalsIgnoreCase(salesmanName));
    }

}
