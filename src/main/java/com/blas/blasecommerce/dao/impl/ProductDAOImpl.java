package com.blas.blasecommerce.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.blas.blasecommerce.dao.ProductDAO;
import com.blas.blasecommerce.entity.Product;
import com.blas.blasecommerce.model.PaginationResult;
import com.blas.blasecommerce.model.ProductModel;
@Transactional
@Repository
public class ProductDAOImpl implements ProductDAO{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Product findProduct(String id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(Product.class);
		crit.add(Restrictions.eq("id", id));
		return (Product) crit.uniqueResult();
	}

	@Override
	public ProductModel findProductModel(String id) {
		// TODO Auto-generated method stub
		Product product = this.findProduct(id);
		if (product == null) {
			return null;
		}
		return new ProductModel(product);
	}

	@Override
	public PaginationResult<ProductModel> queryProducts(int page, int maxResult, int maxNavigationPage) {
		// TODO Auto-generated method stub
		return queryProducts(page, maxResult, maxNavigationPage, null);
	}

	@Override
	public PaginationResult<ProductModel> queryProducts(int page, int maxResult, int maxNavigationPage,
			String likeName) {
		// TODO Auto-generated method stub
		String sql = "Select new " + ProductModel.class.getName() //
				+ "(p.id, p.categoryId, p.createDate, p.name, p.price, p.unitId, p.description) " + " from "//
				+ Product.class.getName() + " p order by p.createDate desc ";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		return new PaginationResult<ProductModel>(query, page, maxResult, maxNavigationPage);
	}

	@Override
	public void save(ProductModel productModel) {
		// TODO Auto-generated method stub
		String id = productModel.getId();
		boolean isNew = false;
		Product product = null;

		if (id != null) {
			product = findProduct(id);
		}
		if (product == null) {
			isNew = true;
			product = new Product(productModel);
		}
		if (!isNew) {
			if (productModel.getImage() != null) {
				byte[] image = productModel.getImage().getBytes();
				if (image != null && image.length > 0) {
					product.setImage(image);
				}
			}
			product.setName(productModel.getName());
			product.setPrice(productModel.getPrice());
			product.setDescription(productModel.getDescription());
		} else {
			System.out.println("Product: " + product.toString());
			sessionFactory.getCurrentSession().persist(product);
		}
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public PaginationResult<ProductModel> queryProductsByCategory(int page, int maxResult, int maxNavigationPage,
			String likeName) {
		// TODO Auto-generated method stub
		String sql = "Select new " + ProductModel.class.getName() //
				+ "(p.id, p.categoryId, p.createDate, p.name, p.price, p.unitId, p.description) " + " from "//
				+ Product.class.getName() + " p where p.categoryId='" + likeName + "' order by p.createDate desc ";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		return new PaginationResult<ProductModel>(query, page, maxResult, maxNavigationPage);
	}

	@Override
	public PaginationResult<ProductModel> queryProductsByCategoryPriceDes(int page, int maxResult, int maxNavigationPage,
			String likeName) {
		// TODO Auto-generated method stub
		String sql = "Select new " + ProductModel.class.getName() //
				+ "(p.id, p.categoryId, p.createDate, p.name, p.price, p.unitId, p.description) " + " from "//
				+ Product.class.getName() + " p where p.categoryId='" + likeName + "' order by p.price desc ";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		return new PaginationResult<ProductModel>(query, page, maxResult, maxNavigationPage);
	}

	@Override
	public PaginationResult<ProductModel> queryProductsByCategoryPriceInc(int page, int maxResult, int maxNavigationPage,
			String likeName) {
		// TODO Auto-generated method stub
		String sql = "Select new " + ProductModel.class.getName() //
				+ "(p.id, p.categoryId, p.createDate, p.name, p.price, p.unitId, p.description) " + " from "//
				+ Product.class.getName() + " p where p.categoryId='" + likeName + "' order by p.price";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		return new PaginationResult<ProductModel>(query, page, maxResult, maxNavigationPage);
	}

	@Override
	public PaginationResult<ProductModel> searchProduct(int page, int maxResult, int maxNavigationPage,
			String likeName) {
		// TODO Auto-generated method stub
		String sql = "Select new " + ProductModel.class.getName() //
				+ "(p.id, p.categoryId, p.createDate, p.name, p.price, p.unitId, p.description) " + " from "//
				+ Product.class.getName() + " p where p.name like '%" + likeName + "%' order by p.createDate desc ";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		return new PaginationResult<ProductModel>(query, page, maxResult, maxNavigationPage);
	}

	@Override
	public PaginationResult<ProductModel> searchProductPriceDes(int page, int maxResult, int maxNavigationPage,
			String likeName) {
		// TODO Auto-generated method stub
		String sql = "Select new " + ProductModel.class.getName() //
				+ "(p.id, p.categoryId, p.createDate, p.name, p.price, p.unitId, p.description) " + " from "//
				+ Product.class.getName() + " p where p.name like '%" + likeName + "%' order by p.price desc";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		return new PaginationResult<ProductModel>(query, page, maxResult, maxNavigationPage);
	}

	@Override
	public PaginationResult<ProductModel> searchProductPriceInc(int page, int maxResult, int maxNavigationPage,
			String likeName) {
		// TODO Auto-generated method stub
		String sql = "Select new " + ProductModel.class.getName() //
				+ "(p.id, p.categoryId, p.createDate, p.name, p.price, p.unitId, p.description) " + " from "//
				+ Product.class.getName() + " p where p.name like '%" + likeName + "%' order by p.price";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		return new PaginationResult<ProductModel>(query, page, maxResult, maxNavigationPage);
	}

}
