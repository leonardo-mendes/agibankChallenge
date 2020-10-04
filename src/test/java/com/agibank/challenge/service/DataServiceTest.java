package com.agibank.challenge.service;

import com.agibank.challenge.domain.Report;
import com.agibank.challenge.util.enums.Splitter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class DataServiceTest {

    DataService dataService = new DataService();

    private final String SALESMAN_LINE = "001ç1234567891234çPedroç50000";
    private final String CLIENT_LINE = "002ç2345675434544345çJose da SilvaçRural";
    private final String CLIENT_ERROR_LINE = "002ç2345675434544345çJose da Silva";
    private final String ORDER_LINE = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro";
    private final String ORDER_ERROR_LINE = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10l]çPedro";


    @Test
    public void should_parser_salesman(){
        Report report = Report.builder().build();
        String[] salesmanArray = SALESMAN_LINE.split(Splitter.ITEM.value);
        dataService.addData(salesmanArray, report);
        Assertions.assertEquals(1, report.countSalesmen());
    }

    @Test
    public void should_set_error_on_parser_salesman(){
        Report report = Report.builder().build();
        String[] salesmanArray = SALESMAN_LINE.split(Splitter.ITEM.value);
        salesmanArray[3] = "test";
        dataService.addData(salesmanArray, report);
        Assertions.assertTrue(report.getError());
    }

    @Test
    public void should_parser_client(){
        Report report = Report.builder().build();
        String[] clientArray = CLIENT_LINE.split(Splitter.ITEM.value);
        dataService.addData(clientArray, report);
        Assertions.assertEquals(1, report.countClients());
    }

    @Test
    public void should_set_error_on_parser_client(){
        Report report = Report.builder().build();
        String[] clientArray = CLIENT_ERROR_LINE.split(Splitter.ITEM.value);
        dataService.addData(clientArray, report);
        Assertions.assertTrue(report.getError());
    }

    @Test
    public void should_parser_order(){
        Report report = Report.builder().build();
        String[] orderArray = ORDER_LINE.split(Splitter.ITEM.value);
        dataService.addData(orderArray, report);
        Assertions.assertEquals(1, report.getOrders().size());
    }

    @Test
    public void should_set_error_on_parser_order(){
        Report report = Report.builder().build();
        String[] orderArray = ORDER_ERROR_LINE.split(Splitter.ITEM.value);
        dataService.addData(orderArray, report);
        Assertions.assertTrue(report.getError());
    }


}
