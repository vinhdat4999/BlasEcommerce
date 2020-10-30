package com.blas.blasecommerce.model;

import java.util.Date;

public class AccountModel {

	private String username;
	private String firstname;
	private String lastname;
	private boolean gender;
	private Date birthdate;
	private String phoneNum;
	private String email;
	private boolean active;
	private String password;
	private String userRole;

	public AccountModel() {
		super();
	}

	public AccountModel(String username, String firstname, String lastname, boolean gender, Date birthdate,
			String phoneNum, String email, boolean active, String password, String userRole) {
		super();
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
		this.birthdate = birthdate;
		this.phoneNum = phoneNum;
		this.email = email;
		this.active = active;
		this.password = password;
		this.userRole = userRole;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "AccountModel [username=" + username + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", gender=" + gender + ", birthdate=" + birthdate + ", phoneNum=" + phoneNum + ", email=" + email
				+ ", active=" + active + ", password=" + password + ", userRole=" + userRole + "]";
	}

}
