package com.cg.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class BillPayment {
	@Id
private int billId;
private String billType;
private double amount;
private LocalDate paymentDate;
@ManyToOne
private Wallet wallet;
public BillPayment(int billId, String billType, double amount, LocalDate paymentDate, Wallet wallet) {
	super();
	this.billId = billId;
	this.billType = billType;
	this.amount = amount;
	this.paymentDate = paymentDate;
	this.wallet = wallet;
}



public BillPayment() {
	super();
}



public int getBillId() {
	return billId;
}
public void setBillId(int billId) {
	this.billId = billId;
}
public String getBillType() {
	return billType;
}
public void setBillType(String billType) {
	this.billType = billType;
}
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}
public LocalDate getPaymentDate() {
	return paymentDate;
}
public void setPaymentDate(LocalDate paymentDate) {
	this.paymentDate = paymentDate;
}
public Wallet getWallet() {
	return wallet;
}
public void setWallet(Wallet wallet) {
	this.wallet = wallet;
}
@Override
public String toString() {
	return "BillPayment [billId=" + billId + ", billType=" + billType + ", amount=" + amount + ", paymentDate="
			+ paymentDate + ", wallet=" + wallet + "]";
}

}
