package com.cg.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Transaction")
public class Transaction {
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int transactionId;
	private String transactionType;
	private LocalDate transactionDate;
	private double amount;
	private String description;
	@JsonIgnore
	@ManyToOne
	private Wallet wallet;

	public Transaction(String transactionType, LocalDate transactionDate, double amount, String description,
			Wallet wallet) {
		super();
		this.transactionType = transactionType;
		this.transactionDate = transactionDate;
		this.amount = amount;
		this.description = description;
		this.wallet = wallet;
	}

	public Transaction() {

	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	@Override
	public String toString() {
		return "Transaction [TransactionId=" + transactionId + ", transactionType=" + transactionType
				+ ", transactionDate=" + transactionDate + ", amount=" + amount + ", Description=" + description
				+ ", wallet=" + wallet + "]";
	}

}
