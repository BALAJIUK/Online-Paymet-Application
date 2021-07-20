package com.cg.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

@Entity
public class Customer {

	private String name;
	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cid;

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
	

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
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
