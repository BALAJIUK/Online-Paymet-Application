package com.cg.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class CustomerToken {
	@Id
private String mobileNo;
private String token;
public CustomerToken(String mobileNo, String token) {
	super();
	this.mobileNo = mobileNo;
	this.token = token;
}
public CustomerToken()
{
	
}
public String getMobileNo() {
	return mobileNo;
}
public void setMobileNo(String mobileNo) {
	this.mobileNo = mobileNo;
}
public String getToken() {
	return token;
}
public void setToken(String token) {
	this.token = token;
}
@Override
public String toString() {
	return "CustomerToken [mobileNo=" + mobileNo + ", token=" + token + "]";
}



}
