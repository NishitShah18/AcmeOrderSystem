package com.acme.domain;

import com.acme.utils.MyDate;

public class Order {
    MyDate orderDate;
    double orderAmount = 0.00;
    String customer;
    String product;
    int quantity;
    public static double taxRate;

    static {
        taxRate = 0.05;
    }

    public Order(MyDate d, double amt, String c, String p, int q) {
        this.orderDate = d;
        this.orderAmount = amt;
        this.customer = c;
        this.product = p;
        this.quantity = q;
    }

    public Order(MyDate d, double amt, String c) {
        this.orderDate = d;
        this.orderAmount = amt;
        this.customer = c;
        this.product = "Anvil";
        this.quantity = 1;
    }

    public String toString() {
        return quantity + " ea. " + product + " for " + customer;
    }

    public static void setTaxRate(double taxRate) {
        Order.taxRate = taxRate;
    }

    public static void computeTaxOn(double anAmount) {
        System.out.println("The tax for " + anAmount + " is: " + anAmount * Order.taxRate);
    }

    public double computeTax() {
        System.out.println("The tax for this order is: " + orderAmount * Order.taxRate);
        return orderAmount * Order.taxRate;
    }
}
