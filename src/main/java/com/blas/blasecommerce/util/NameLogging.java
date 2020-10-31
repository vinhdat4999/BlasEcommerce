package com.blas.blasecommerce.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class NameLogging {

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

	public String getNameLogging() {
		try {
			String username = "";
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				username = ((UserDetails) principal).getUsername();

			} else {
				username = principal.toString();
			}
			String sql = " select firstname, lastname from account where username = '" + username + "'";
			Statement sta = getConnection().createStatement();
			ResultSet RS = sta.executeQuery(sql);
			if (RS.next() == true) {
				return RS.getString("lastname");
			}
			sta.close();
			closeConnection();
		} catch (SQLException ex) {
		}
		return null;
	}

}