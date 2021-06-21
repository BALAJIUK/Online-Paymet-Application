package com.cg.security.models;

public class JwtRequest {

	private String mobileNumber;
	private String password;
	public JwtRequest(String mobileNumber, String password) {
		super();
		this.mobileNumber = mobileNumber;
		this.password = password;
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
	@Override
	public String toString() {
		return "JwtRequest [mobileNumber=" + mobileNumber + ", password=" + password + "]";
	}
	
	
	
}
