package com.blas.blasecommerce.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.blas.blasecommerce.dao.ProductImageDAO;
import com.blas.blasecommerce.entity.Cart;
import com.blas.blasecommerce.entity.Product;
import com.blas.blasecommerce.entity.ProductImage;
import com.blas.blasecommerce.model.CartModel;
import com.blas.blasecommerce.model.PaginationResult;
import com.blas.blasecommerce.model.ProductImageModel;
import com.blas.blasecommerce.model.ProductModel;

@Transactional
public class ProductImageDAOImpl implements ProductImageDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public ProductImage findProductImage(String imageId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(ProductImage.class);
		crit.add(Restrictions.eq("id", imageId));
		return (ProductImage) crit.uniqueResult();
	}

	@Override
	public ProductImageModel findProductImageModel(String imageId) {
		// TODO Auto-generated method stub
		ProductImage productImage = this.findProductImage(imageId);
		if (productImage == null) {
			return null;
		}
		return new ProductImageModel(productImage);
	}

	@Override
	public List<ProductImageModel> getImageIdList(String productId) {
		// TODO Auto-generated method stub
		String sql = "select new " + ProductImageModel.class.getName() + "(p.id, p.productId) from "
				+ ProductImage.class.getName() + " p where p.productId ='" + productId + "'";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		List<ProductImageModel> list = query.list();
		return list;
	}

	@Override
	public void deleteImage(String imageId) {
		// TODO Auto-generated method stub
		String hql = "DELETE FROM " + ProductImage.class.getName() + " WHERE id = '" + imageId + "'";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public void addImage(ProductImage productImage) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.persist(productImage);
	}

	@Override
	public PaginationResult<ProductImageModel> queryProductImages(int page, int maxResult, int maxNavigationPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaginationResult<ProductImageModel> queryProductImages(int page, int maxResult, int maxNavigationPage,
			String likeName) {
		// TODO Auto-generated method stub
		String sql = "Select new " + ProductImageModel.class.getName() //
				+ "(p.id, p.productId) " + " from " + ProductImage.class.getName() + " p";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		return new PaginationResult<ProductImageModel>(query, page, maxResult, maxNavigationPage);
	}
}
