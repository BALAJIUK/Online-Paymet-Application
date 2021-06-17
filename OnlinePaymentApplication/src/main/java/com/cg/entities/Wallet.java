package com.cg.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Wallet {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
private int walletId;
private BigDecimal balance;
@Transient
private BankAccount bankaccount=new BankAccount();
public Wallet( BigDecimal balance) {
	super();
	this.balance = balance;
}
public Wallet()
{
	
}
public int getWalletId() {
	return walletId;
}
public void setWalletId(int walletId) {
	this.walletId = walletId;
}
public BigDecimal getBalance() {
	return balance;
}
public void setBalance(BigDecimal balance) {
	this.balance = balance;
}
@JsonIgnore
public BankAccount getBankaccount() {
	return bankaccount;
}
public void setBankaccount(BankAccount bankaccount) {
	this.bankaccount = bankaccount;
}
@Override
public String toString() {
	return "Wallet [walletId=" + walletId + ", balance=" + balance + "]";
}


}
