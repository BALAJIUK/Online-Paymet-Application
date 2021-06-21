package com.cg.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class BankAccount {
	@Id@Min(100000)@Max(999999)
	private int accountNo;
	@NotNull
	@Size(min=10,max=11 , message = "ifsc code must contain 10 or 11 charecters" )
	private String ifscCode;
	@NotNull
	private String bankName;
	@Min(100)
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
