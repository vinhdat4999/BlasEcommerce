package com.blas.blasecommerce.dao;

import com.blas.blasecommerce.entity.ReceiverInfo;
import com.blas.blasecommerce.entity.User;
import com.blas.blasecommerce.model.UserModel;

public interface UserDAO {
	
	public User findUser(String username);

	public UserModel findUserModel(String username);

	public void saveUser(User user);

	public void saveUser(User user , ReceiverInfo receiverInfo);
}
