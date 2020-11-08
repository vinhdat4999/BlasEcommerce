package com.blas.blasecommerce.dao;

import java.time.LocalDateTime;

import com.blas.blasecommerce.entity.Authentication;
import com.blas.blasecommerce.model.AuthenticationModel;

public interface AuthenticationDAO {

	public Authentication findAuthentication(String username);
	public AuthenticationModel findAuthenticationModel(String username);
	public void save(String username, String code);
	public void save(String username, LocalDateTime expire);
	public boolean isValidAuthentication(String username, String code);

}
