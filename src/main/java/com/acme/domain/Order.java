package com.acme.domain;

import com.acme.utils.HolidayOrdersNotAllowedException;
import com.acme.utils.MyDate;

public class Order {
    private MyDate orderDate;
    private double orderAmount = 0.00;
    private String customer;
    private Product product;
    private int quantity;
    public static double taxRate;
    private static Rushable rushable;

    static {
        taxRate = 0.05;
    }

    public Order(MyDate d, double amt, String c, Product p, int q) {
        try {
            setOrderDate(d);
        } catch (HolidayOrdersNotAllowedException e) {
            System.out.println("The order date for an order cannot be a  holiday!  Application closing.");
            System.exit(0);
        }
        this.orderAmount = amt;
        this.customer = c;
        this.product = p;
        this.quantity = q;
    }

//	public Order(MyDate d, double amt, String c){
//		this.orderDate = d;
//		this.orderAmount = amt;
//		this.customer = c;
//		this.product = "Anvil";
//		this.quantity = 1;
//	}

    public MyDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(MyDate orderDate) throws HolidayOrdersNotAllowedException {
        System.out.println("setOrderDate was called");
        if (isHoliday(orderDate)) {
            System.out.println("Order date, " + orderDate + ", cannot be set to a holiday!");
            throw new HolidayOrdersNotAllowedException(orderDate);
        } else {
            this.orderDate = orderDate;
        }
    }

    public double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(double orderAmount) {
        if (orderAmount > 0) {
            this.orderAmount = orderAmount;
        } else {
            System.out.println("Attempting to set the order amount to a value less than or equal to zero");
        }
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Good product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity > 0) {
            this.quantity = quantity;
        } else {
            System.out.println("Attempting to set the quantity to a value less than or equal to zero");
        }
    }

    public static Rushable getRushable() {
        return rushable;
    }

    public static void setRushable(Rushable rushable) {
        Order.rushable = rushable;
    }

    public static double getTaxRate() {
        return taxRate;
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

    public char jobSize() {
        if (quantity <= 25) {
            return 'S';
        } else if (quantity <= 75) {
            return 'M';
        } else if (quantity <= 150) {
            return 'L';
        }
        return 'X';
    }

    public double computeTotal() {
        double discount = 0;
        switch (jobSize()) {
            case 'M':
                discount = 0.01;
                break;
            case 'L':
                discount = 0.02;
                break;
            case 'X':
                discount = 0.03;
                break;
            default:
                break;
        }
        discount = orderAmount * discount;
        if (orderAmount <= 1500) {
            return orderAmount - discount + computeTax();
        }
        return orderAmount - discount;
    }

//	public double computeTotal(){
//		double total = orderAmount;
//		switch (jobSize()){
//			case 'M': total = total - (orderAmount * 0.01);
//				break;
//			case 'L': total = total - (orderAmount * 0.02);
//				break;
//			case 'X': total = total - (orderAmount * 0.03);
//				break;
//		}
//		if (orderAmount <= 1500){
//			total = total + computeTax();
//		}
//		return total;
//	}

    public boolean isPriorityOrder() {
        boolean priorityOrder = false;
        if (rushable != null) {
            priorityOrder = rushable.isRushable(orderDate, orderAmount);
        }
        return priorityOrder;
    }

    private boolean isHoliday(MyDate proposedDate) {
        for (MyDate holiday : MyDate.getHolidays()) {
            if (holiday.equals(proposedDate)) {
                System.out.println("Holiday");
                return true;
            }
        }
        return false;
    }


}

