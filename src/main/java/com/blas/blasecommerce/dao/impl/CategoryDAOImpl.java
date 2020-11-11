package com.blas.blasecommerce.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.blas.blasecommerce.dao.CategoryDAO;
import com.blas.blasecommerce.entity.Category;
import com.blas.blasecommerce.model.CategoryModel;

@Transactional
public class CategoryDAOImpl implements CategoryDAO{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<CategoryModel> getCategoryList() {
		// TODO Auto-generated method stub
		String sql = "select new " + CategoryModel.class.getName() + "(c.category) from "
				+ Category.class.getName() + " c";
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		List<CategoryModel> list = query.list();
		return list;
	}

}
