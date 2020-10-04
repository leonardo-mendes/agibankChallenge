package com.agibank.challenge.service;

import com.agibank.challenge.domain.Report;
import com.agibank.challenge.parser.DataParser;
import com.agibank.challenge.parser.impl.ClientParserImpl;
import com.agibank.challenge.parser.impl.OrderParserImpl;
import com.agibank.challenge.parser.impl.SalesmanParserImpl;
import com.agibank.challenge.util.enums.DataIdentifier;
import com.agibank.challenge.util.enums.Splitter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DataService {

    private HashMap<String, DataParser> dataRecover = new HashMap<>();

    public DataService() {
        dataRecover.put(DataIdentifier.SALESMAN.code, new SalesmanParserImpl());
        dataRecover.put(DataIdentifier.CLIENT.code, new ClientParserImpl());
        dataRecover.put(DataIdentifier.ORDER.code, new OrderParserImpl());
    }

    public void addData(String[] data, Report report) {
        try {
            report.addData(dataRecover.get(data[0]).parser(data));
        } catch (Exception e){
            report.setError(Boolean.TRUE);
            log.error("Failure to process line {} in {} file, cause: {}", buildErrorLine(data), report.getFileName(), e.getMessage());
        }
    }

    private String buildErrorLine(String[] data){
        String error = Arrays.stream(data).map(s -> s.concat(Splitter.ITEM.value)).collect(Collectors.joining());
        return error.substring(0, error.length() - 1);
    }
}
