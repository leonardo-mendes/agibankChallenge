package com.agibank.challenge.processor;

import com.agibank.challenge.domain.Report;

import java.util.List;

public interface Processor {

    List<Report> moveForward(List<Report> reports);
}
