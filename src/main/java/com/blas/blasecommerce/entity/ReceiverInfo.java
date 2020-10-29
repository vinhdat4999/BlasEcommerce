package com.blas.blasecommerce.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "receiverinfo")
public class ReceiverInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String username;
	private String receiverName;
	private String receiverPhone;
	private String receiverEmail;
	private String receiverAddress;

	@Id
	@Column(name = "id", length = 50, nullable = false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "username", length = 20, nullable = false)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "receiverName", length = 50, nullable = false)
	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	@Column(name = "receiverPhone", length = 15, nullable = false)
	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	@Column(name = "receiverEmail", length = 50)
	public String getReceiverEmail() {
		return receiverEmail;
	}

	public void setReceiverEmail(String receiverEmail) {
		this.receiverEmail = receiverEmail;
	}

	@Column(name = "receiverAddress", length = 150, nullable = false)
	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	@Override
	public String toString() {
		return "ReceiverInfo [id=" + id + ", username=" + username + ", receiverName=" + receiverName
				+ ", receiverPhone=" + receiverPhone + ", receiverEmail=" + receiverEmail + ", receiverAddress="
				+ receiverAddress + "]";
	}

}
