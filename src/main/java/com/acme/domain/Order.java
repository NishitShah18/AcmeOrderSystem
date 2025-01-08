package com.acme.domain;

import com.acme.utils.MyDate;

public class Order {
	private MyDate orderDate;
	private double orderAmount = 0.00;
	private String customer;
	private Good product;
	private int quantity;
	public static double taxRate;

	static {
		taxRate = 0.05;
	}

	public Order(MyDate d, double amt, String c, Good p, int q){
		this.orderDate=d;
		this.orderAmount=amt;
		this.customer=c;
		this.product=p;
		this.quantity=q;
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

	public void setOrderDate(MyDate orderDate) {
		this.orderDate = orderDate;
	}

	public double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(double orderAmount) {
		if(orderAmount>0) {
			this.orderAmount = orderAmount;
		}
		else {
			System.out.println("Attempting to set the order amount to a value less than or equal to zero");
		}
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public Good getProduct() {
		return product;
	}

	public void setProduct(Good product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		if(quantity>0) {
			this.quantity = quantity;
		}
		else {
			System.out.println("Attempting to set the quantity to a value less than or equal to zero");
		}
	}

	public static double getTaxRate() {
		return taxRate;
	}

	public String toString(){
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
		switch (jobSize())
		{
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

}

