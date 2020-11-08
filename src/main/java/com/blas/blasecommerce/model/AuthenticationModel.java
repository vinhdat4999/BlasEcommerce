package com.blas.blasecommerce.model;

import java.time.LocalDateTime;

import com.blas.blasecommerce.entity.Authentication;

public class AuthenticationModel {

	private String username;
	private String code;
	private LocalDateTime timeExpire;

	public AuthenticationModel() {
		super();
	}

	public AuthenticationModel(String username, String code, LocalDateTime timeExpire) {
		super();
		this.username = username;
		this.code = code;
		this.timeExpire = timeExpire;
	}

	public AuthenticationModel(Authentication authencation) {
		this.username = authencation.getUsername();
		this.code = authencation.getCode();
		this.timeExpire = authencation.getTimeExpire();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LocalDateTime getTimeExpire() {
		return timeExpire;
	}

	public void setTimeExpire(LocalDateTime timeExpire) {
		this.timeExpire = timeExpire;
	}

	@Override
	public String toString() {
		return "AuthencationModel [username=" + username + ", code=" + code + ", timeExpire="
				+ timeExpire + "]";
	}

}
