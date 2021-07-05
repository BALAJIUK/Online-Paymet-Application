package com.cg.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Customer {
	private String name;
	@Id
	private String mobileNumber;
	private String password;
	@OneToOne
	private Wallet wallet;

	public Customer(String name, String mobileNumber, String password, Wallet wallet) {
		super();
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.password = password;
		this.wallet = wallet;
	}

	public Customer() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", mobileNumber=" + mobileNumber + ", password=" + password + ", wallet="
				+ wallet + "]";
	}

}
