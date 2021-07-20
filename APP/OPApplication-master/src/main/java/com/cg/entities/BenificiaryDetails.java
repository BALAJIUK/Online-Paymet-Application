package com.cg.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class BenificiaryDetails {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int benificiaryId;

	private String name;

	private String mobileNumber;
	@JsonIgnore
	@ManyToOne
	private Wallet wallet;

	public BenificiaryDetails(String name, String mobileNumber, Wallet wallet) {
		super();
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.wallet = wallet;
	}

	public BenificiaryDetails() {

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

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	@Override
	public String toString() {
		return "BeneficiaryDetails [name=" + name + ", mobileNumber=" + mobileNumber + ", wallet=" + wallet + "]";
	}

}
