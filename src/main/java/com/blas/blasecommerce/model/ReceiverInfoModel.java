package com.blas.blasecommerce.model;

import com.blas.blasecommerce.entity.ReceiverInfo;

public class ReceiverInfoModel {
	private String id;
	private String username;
	private String receiverName;
	private String receiverPhone;
	private String receiverEmail;
	private String receiverAddress;
	private boolean active;

	public ReceiverInfoModel() {
		super();
	}

	public ReceiverInfoModel(String id, String username, String receiverName, String receiverPhone,
			String receiverEmail, String receiverAddress, boolean active) {
		super();
		this.id = id;
		this.username = username;
		this.receiverName = receiverName;
		this.receiverPhone = receiverPhone;
		this.receiverEmail = receiverEmail;
		this.receiverAddress = receiverAddress;
		this.active = active;
	}

	public ReceiverInfoModel(ReceiverInfo receiverInfo) {
		this.id = receiverInfo.getId();
		this.username = receiverInfo.getUsername();
		this.receiverName = receiverInfo.getReceiverName();
		this.receiverPhone = receiverInfo.getReceiverPhone();
		this.receiverEmail = receiverInfo.getReceiverEmail();
		this.receiverAddress = receiverInfo.getReceiverAddress();
		this.active = receiverInfo.isActive();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public String getReceiverEmail() {
		return receiverEmail;
	}

	public void setReceiverEmail(String receiverEmail) {
		this.receiverEmail = receiverEmail;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "ReceiverInfoModel [id=" + id + ", username=" + username + ", receiverName=" + receiverName
				+ ", receiverPhone=" + receiverPhone + ", receiverEmail=" + receiverEmail + ", receiverAddress="
				+ receiverAddress + ", active=" + active + "]";
	}

}
