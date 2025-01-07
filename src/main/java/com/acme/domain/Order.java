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

	public Order(MyDate d, double amt, String c, String p, int q){
		this.orderDate=d;
		this.orderAmount=amt;
		this.customer=c;
		this.product=p;
		this.quantity=q;
	}

	public Order(MyDate d, double amt, String c){
		this.orderDate = d;
		this.orderAmount = amt;
		this.customer = c;
		this.product = "Anvil";
		this.quantity = 1;
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

