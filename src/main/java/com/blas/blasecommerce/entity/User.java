package com.blas.blasecommerce.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.blas.blasecommerce.model.UserModel;

@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
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

	public User() {
		super();
	}

	public User(String username, String firstname, String lastname, boolean gender, Date birthdate, String phoneNum,
			String email, boolean active, String password, String userRole) {
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

	public User(UserModel userModel) {
		this.username = userModel.getUsername();
		this.firstname = userModel.getFirstname();
		this.lastname = userModel.getLastname();
		this.gender = userModel.isGender();
		this.birthdate = userModel.getBirthdate();
		this.phoneNum = userModel.getPhoneNum();
		this.email = userModel.getEmail();
		this.active = userModel.isActive();
		this.password = userModel.getPassword();
		this.userRole = userModel.getUserRole();
	}

	@Id
	@Column(name = "username", length = 20, nullable = false)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "firstname", length = 30)
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Column(name = "lastname", length = 20, nullable = false)
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Column(name = "gender", nullable = false)
	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "birthdate", nullable = false)
	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	@Column(name = "phoneNum", length = 15)
	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	@Column(name = "email", length = 20)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "active")
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Column(name = "password", length = 40, nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "userRole", length = 10, nullable = false)
	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", firstname=" + firstname + ", lastname=" + lastname + ", gender="
				+ gender + ", birthdate=" + birthdate + ", phoneNum=" + phoneNum + ", email=" + email + ", active="
				+ active + ", password=" + password + ", userRole=" + userRole + "]";
	}

}
