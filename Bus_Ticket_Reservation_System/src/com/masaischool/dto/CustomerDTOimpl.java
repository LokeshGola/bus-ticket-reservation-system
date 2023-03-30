package com.masaischool.dto;

public class CustomerDTOimpl implements CustomerDTO{
	private String cusId;
	private String firstname;
	private String lastname;
	private String address;
	private long mobile;
	private String username;
	private String password;
	public CustomerDTOimpl(String cusId, String firstname, String lastname, String address, long mobile,
			String username, String password) {
		super();
		this.cusId = cusId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.mobile = mobile;
		this.username = username;
		this.password = password;
	}
	public String getCusId() {
		return cusId;
	}
	public void setCusId(String cusId) {
		this.cusId = cusId;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Customer Id=" + cusId + ", firstname= " + firstname + ", lastname= " + lastname + ", address="
				+ address + ", mobile=" + mobile + ", username=" + username + ", password=" + password + "";
	}
	
	
}
