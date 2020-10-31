package com.blas.blasecommerce.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class NumberOfProduct {
	private Connection conn;

	public Connection getConnection() {
		try {
			this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/blasecommerce", "root", "");
		} catch (SQLException ex) {
		}
		return this.conn;
	}

	public void closeConnection() {
		try {
			this.conn.close();
		} catch (SQLException ex) {
		}
	}

	public int getNumbersOfProductInCart() {
		int numberOfProduct = 0;
		try {
			String username = "";
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				username = ((UserDetails) principal).getUsername();

			} else {
				username = principal.toString();
			}
			String sql = " select quanity from cart where username = '" + username + "'";
			Statement sta = getConnection().createStatement();
			ResultSet RS = sta.executeQuery(sql);
			while(RS.next() == true) {
				numberOfProduct+=RS.getInt("quanity");
			}
			sta.close();
			closeConnection();
		} catch (SQLException ex) {
		}
		return numberOfProduct;
	}
}
