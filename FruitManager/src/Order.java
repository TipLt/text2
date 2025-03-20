/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.ArrayList;
import java.util.List;

public class Order {

    private String customerName;
    public List<Fruit> orderList = new ArrayList<>();

    public Order() {
    }

    public Order(String customerName, List<Fruit> orderList) {
        this.customerName = customerName;
        this.orderList = orderList;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<Fruit> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Fruit> orderList) {
        this.orderList = orderList;

    }
}
