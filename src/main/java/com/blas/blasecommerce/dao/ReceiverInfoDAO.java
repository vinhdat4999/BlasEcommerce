package com.blas.blasecommerce.dao;

import com.blas.blasecommerce.entity.ReceiverInfo;
import com.blas.blasecommerce.model.PaginationResult;
import com.blas.blasecommerce.model.ReceiverInfoModel;

public interface ReceiverInfoDAO {

	public ReceiverInfo findReceiverInfo(String id);

	public ReceiverInfoModel findReceiverInfoModelById(String id);

	public ReceiverInfoModel findReceiverInfoModelByUsername(String username);

	public PaginationResult<ReceiverInfoModel> queryReceiverInfos(int page, int maxResult, int maxNavigationPage,
			String username);

	public void save(ReceiverInfoModel receiverInfoModel);

	public void delete(String receiverInfoId);
}
