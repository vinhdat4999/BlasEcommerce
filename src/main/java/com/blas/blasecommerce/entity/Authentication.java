package com.blas.blasecommerce.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.blas.blasecommerce.model.AuthenticationModel;

@Entity
@Table(name = "authentication")
public class Authentication implements Serializable {

	private static final long serialVersionUID = 1L;
	private String username;
	private String code;
	private LocalDateTime timeExpire;

	public Authentication() {
		super();
	}

	public Authentication(String username, String code, LocalDateTime timeExpire) {
		super();
		this.username = username;
		this.code = code;
		this.timeExpire = timeExpire;
	}

	public Authentication(AuthenticationModel authencationModel) {
		this.username = authencationModel.getUsername();
		this.code = authencationModel.getCode();
		this.timeExpire = authencationModel.getTimeExpire();
	}
	@Id
	@Column(name = "username", length = 20, nullable = false)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "code", length = 10, nullable = false)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "timeExpire", nullable = false)
	public LocalDateTime getTimeExpire() {
		return timeExpire;
	}

	public void setTimeExpire(LocalDateTime timeExpire) {
		this.timeExpire = timeExpire;
	}

	@Override
	public String toString() {
		return "Authencation [username=" + username + ", code=" + code + ", timeExpire=" + timeExpire
				+ "]";
	}
}
