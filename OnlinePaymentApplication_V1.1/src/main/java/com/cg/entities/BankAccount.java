package com.cg.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class BankAccount {
	@Id
	private int accountNo;
	private String ifscCode;
	private String bankName;
	private double balance;
	@JsonIgnore
	@ManyToOne
	private Wallet wallet;

	public BankAccount(int accountNo, String ifscCode, String bankName, double balance, Wallet wallet) {
		super();
		this.accountNo = accountNo;
		this.ifscCode = ifscCode;
		this.bankName = bankName;
		this.balance = balance;
		this.wallet = wallet;
	}

	public BankAccount() {

	}

	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	@Override
	public String toString() {
		return "BankAccount [accountNo=" + accountNo + ", ifscCode=" + ifscCode + ", bankName=" + bankName
				+ ", balance=" + balance + ", wallet=" + wallet + "]";
	}

}
