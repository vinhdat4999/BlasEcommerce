package com.blas.blasecommerce.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.blas.blasecommerce.dao.ReceiverInfoDAO;
import com.blas.blasecommerce.entity.ReceiverInfo;
import com.blas.blasecommerce.model.PaginationResult;
import com.blas.blasecommerce.model.ReceiverInfoModel;

public class ReceiverInfoDAOImpl implements ReceiverInfoDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public ReceiverInfo findReceiverInfo(String id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(ReceiverInfo.class);
		crit.add(Restrictions.eq("id", id));
		return (ReceiverInfo) crit.uniqueResult();
	}

	@Override
	public ReceiverInfoModel findReceiverInfoModelById(String id) {
		// TODO Auto-generated method stub
		ReceiverInfo receiverInfo = this.findReceiverInfo(id);
		if (receiverInfo == null) {
			return null;
		}
		return new ReceiverInfoModel(receiverInfo);
	}

	@Override
	public ReceiverInfoModel findReceiverInfoModelByUsername(String username) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria queryCriteria = session.createCriteria(ReceiverInfo.class);
		queryCriteria.add(Restrictions.eq("username", username));
		queryCriteria.setFirstResult(0);
		queryCriteria.setMaxResults(1);
		List<ReceiverInfo> list =  queryCriteria.list();
		return new ReceiverInfoModel(list.get(0));
	}

	@Override
	public PaginationResult<ReceiverInfoModel> queryReceiverInfos(int page, int maxResult, int maxNavigationPage,
			String username) {
		// TODO Auto-generated method stub

		String sql = "select new " + ReceiverInfoModel.class.getName()
				+ "(r.id, r.username, r.receiverName, r.receiverPhone, r.receiverEmail, r.receiverAddress) from "
				+ ReceiverInfo.class.getName() + " r where r.username = '" + username + "'";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		return new PaginationResult<ReceiverInfoModel>(query, page, maxResult, maxNavigationPage);
	}

}
