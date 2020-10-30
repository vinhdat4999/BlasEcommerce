package com.blas.blasecommerce.model;

import java.time.LocalDateTime;
import java.util.Date;

public class OrderModel {

	private String id;
	private String username;
	private String receiverInfoId;
	private LocalDateTime orderTime;
	private String status;
	private Date expectShipDate;
	private String description;
	private String code;
	private String coupon;
	private int coins;
	private double total;

	public OrderModel() {
		super();
	}

	public OrderModel(String id, String username, String receiverInfoId, LocalDateTime orderTime, String status,
			Date expectShipDate, String description, String code, String coupon, int coins, double total) {
		super();
		this.id = id;
		this.username = username;
		this.receiverInfoId = receiverInfoId;
		this.orderTime = orderTime;
		this.status = status;
		this.expectShipDate = expectShipDate;
		this.description = description;
		this.code = code;
		this.coupon = coupon;
		this.coins = coins;
		this.total = total;
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

	public String getReceiverInfoId() {
		return receiverInfoId;
	}

	public void setReceiverInfoId(String receiverInfoId) {
		this.receiverInfoId = receiverInfoId;
	}

	public LocalDateTime getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(LocalDateTime orderTime) {
		this.orderTime = orderTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getExpectShipDate() {
		return expectShipDate;
	}

	public void setExpectShipDate(Date expectShipDate) {
		this.expectShipDate = expectShipDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCoupon() {
		return coupon;
	}

	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "OrderModel [id=" + id + ", username=" + username + ", receiverInfoId=" + receiverInfoId + ", orderTime="
				+ orderTime + ", status=" + status + ", expectShipDate=" + expectShipDate + ", description="
				+ description + ", code=" + code + ", coupon=" + coupon + ", coins=" + coins + ", total=" + total + "]";
	}

}
