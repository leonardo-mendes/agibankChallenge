package com.agibank.challenge.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ReportTest {

    @Test
    public void should_count_clients(){
        Report report = Report.builder().build();
        report.getClients().add(buildClient());
        Assertions.assertEquals(1, report.countClients());
    }

    @Test
    public void should_count_salesmen(){
        Report report = Report.builder().build();
        report.getSalesmen().add(buildSalesman());
        Assertions.assertEquals(1, report.countSalesmen());
    }

    @Test
    public void should_retrieve_the_highest_and_lowest_sale(){
        Report report = Report.builder().build();
        Order firstOrder = buildOrder(List.of(buildProduct(1, 5.0)));
        Order secondOrder = buildOrder(List.of(buildProduct(1, 6.0)));
        report.getOrders().addAll(List.of(firstOrder, secondOrder));
        Assertions.assertEquals(secondOrder, report.getHighestSale());
        Assertions.assertEquals(firstOrder, report.getLowestSale());
    }

    @Test
    public void should_add_data_in_report() {
        Report report = Report.builder().build();
        report.addData(buildClient());
        report.addData(buildOrder(List.of()));
        report.addData(buildSalesman());
        Assertions.assertEquals(1, report.countClients());
        Assertions.assertEquals(1, report.countClients());
        Assertions.assertEquals(1, report.getOrders().size());
    }

    private Client buildClient(){
        return Client.builder()
                .business("test")
                .cnpj("123456789")
                .name("user")
                .build();
    }

    private Salesman buildSalesman(){
        return Salesman.builder()
                .name("user")
                .cpf("123456789")
                .salary(50000.00)
                .build();
    }

    private Order buildOrder(List<Product> products){
        return Order.builder()
                .salesId("1")
                .salesmanName("user")
                .products(products)
                .build();
    }

    private Product buildProduct(Integer quantity, Double price){
        return Product.builder()
                .itemId("1")
                .quantity(quantity)
                .value(price)
                .build();
    }

}