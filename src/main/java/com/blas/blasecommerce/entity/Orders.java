package com.blas.blasecommerce.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "orders")
public class Orders implements Serializable {
	
	private static final long serialVersionUID = 1L;
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

	@Column(name = "receiverInfoId", length = 50, nullable = false)
	public String getReceiverInfoId() {
		return receiverInfoId;
	}

	public void setReceiverInfoId(String receiverInfoId) {
		this.receiverInfoId = receiverInfoId;
	}

	@Column(name = "orderTime", nullable = false)
	public LocalDateTime getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(LocalDateTime orderTime) {
		this.orderTime = orderTime;
	}

	@Column(name = "status", length = 30, nullable = false)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "expectShipDate", nullable = false)
	public Date getExpectShipDate() {
		return expectShipDate;
	}

	public void setExpectShipDate(Date expectShipDate) {
		this.expectShipDate = expectShipDate;
	}

	@Column(name = "description", length = Integer.MAX_VALUE, nullable = true)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "code", length = 20, nullable = false)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "coupon", length = 20, nullable = false)
	public String getCoupon() {
		return coupon;
	}

	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}

	@Column(name = "coins", nullable = false)
	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	@Column(name = "total", length = Integer.MAX_VALUE, nullable = true)
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", username=" + username + ", receiverInfoId=" + receiverInfoId + ", orderTime="
				+ orderTime + ", status=" + status + ", expectShipDate=" + expectShipDate + ", description="
				+ description + ", code=" + code + ", coupon=" + coupon + ", coins=" + coins + ", total=" + total + "]";
	}

}
