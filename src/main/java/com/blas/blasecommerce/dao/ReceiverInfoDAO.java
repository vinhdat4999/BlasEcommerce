package com.blas.blasecommerce.dao;

import com.blas.blasecommerce.entity.ReceiverInfo;
import com.blas.blasecommerce.model.ReceiverInfoModel;

public interface ReceiverInfoDAO {

	public ReceiverInfo findReceiverInfo(String id);
	public ReceiverInfoModel findReceiverInfoModelById(String id);
	public ReceiverInfoModel findReceiverInfoModelByUsername(String username);
}
