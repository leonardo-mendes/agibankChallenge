package com.agibank.challenge.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import static java.lang.Boolean.TRUE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Report {

    private String fileName;
    private Boolean error;
    private final List<Salesman> salesmen = new LinkedList<>();
    private final List<Client> clients = new LinkedList<>();
    private final List<Order> orders = new LinkedList<>();

    public Integer countClients(){
        return clients.size();
    }

    public Integer countSalesmen(){
        return salesmen.size();
    }

    public Order getHighestSale(){
        return orders.parallelStream()
                .max(Comparator.comparing(Order::getProductsAmount))
                .orElseThrow(NoSuchElementException::new);
    }

    public Order getLowestSale(){
        return orders.parallelStream()
                .min(Comparator.comparing(Order::getProductsAmount))
                .orElseThrow(NoSuchElementException::new);
    }

    public void addData(Object data){
        if(data instanceof Salesman){
            salesmen.add((Salesman) data);
        }else if(data instanceof Client){
            clients.add((Client) data);
        } else {
            orders.add((Order) data);
        }
    }
}
