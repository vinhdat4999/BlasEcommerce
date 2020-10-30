package com.blas.blasecommerce.model;

public class ReceiverInfoModel {
	private String id;
	private String username;
	private String receiverName;
	private String receiverPhone;
	private String receiverEmail;
	private String receiverAddress;

	public ReceiverInfoModel() {
		super();
	}

	public ReceiverInfoModel(String id, String username, String receiverName, String receiverPhone,
			String receiverEmail, String receiverAddress) {
		super();
		this.id = id;
		this.username = username;
		this.receiverName = receiverName;
		this.receiverPhone = receiverPhone;
		this.receiverEmail = receiverEmail;
		this.receiverAddress = receiverAddress;
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

	@Override
	public String toString() {
		return "ReceiverInfoModel [id=" + id + ", username=" + username + ", receiverName=" + receiverName
				+ ", receiverPhone=" + receiverPhone + ", receiverEmail=" + receiverEmail + ", receiverAddress="
				+ receiverAddress + "]";
	}

}
