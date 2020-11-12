package com.blas.blasecommerce.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.blas.blasecommerce.dao.ProductDAO;
import com.blas.blasecommerce.entity.Product;
import com.blas.blasecommerce.model.PaginationResult;
import com.blas.blasecommerce.model.ProductModel;

@Transactional
public class ProductDAOImpl implements ProductDAO {

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
				+ "(p.id, p.category, p.createDate, p.name, p.price, p.description, p.isActive) " + " from "//
				+ Product.class.getName() + " p where isActive=1";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		return new PaginationResult<ProductModel>(query, page, maxResult, maxNavigationPage);
	}

	@Override
	public PaginationResult<ProductModel> queryProductsByCategory(int page, int maxResult, int maxNavigationPage,
			String likeName) {
		// TODO Auto-generated method stub
		String sql = "Select new " + ProductModel.class.getName() //
				+ "(p.id, p.category, p.createDate, p.name, p.price, p.description, p.isActive) " + " from "//
				+ Product.class.getName() + " p where p.category='" + likeName + "' and p.isActive=1";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		return new PaginationResult<ProductModel>(query, page, maxResult, maxNavigationPage);
	}

	@Override
	public PaginationResult<ProductModel> queryProductsByCategorySortPrice(int page, int maxResult,
			int maxNavigationPage, String likeName, String sortType) {
		// TODO Auto-generated method stub
		String sql = "Select new " + ProductModel.class.getName() //
				+ "(p.id, p.category, p.createDate, p.name, p.price, p.description, p.isActive) " + " from "//
				+ Product.class.getName() + " p where p.category='" + likeName + "' and p.isActive=1 order by p.price "
				+ sortType;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		return new PaginationResult<ProductModel>(query, page, maxResult, maxNavigationPage);
	}

	@Override
	public PaginationResult<ProductModel> searchProduct(int page, int maxResult, int maxNavigationPage,
			String likeName) {
		// TODO Auto-generated method stub
		String sql = "Select new " + ProductModel.class.getName()
				+ "(p.id, p.category, p.createDate, p.name, p.price, p.description, p.isActive) " + "from "
				+ Product.class.getName() + " p where p.name like '%" + likeName
				+ "%' and p.isActive=1 order by p.createDate desc";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		return new PaginationResult<ProductModel>(query, page, maxResult, maxNavigationPage);
	}

	@Override
	public PaginationResult<ProductModel> searchProductSortPrice(int page, int maxResult, int maxNavigationPage,
			String likeName, String sortType) {
		// TODO Auto-generated method stub
		String sql = "Select new " + ProductModel.class.getName() //
				+ "(p.id, p.category, p.createDate, p.name, p.price, p.description, p.isActive) " + " from "//
				+ Product.class.getName() + " p where p.name like '%" + likeName
				+ "%' and p.isActive=1 order by p.price " + sortType;
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
			product.setCategory(productModel.getCategory());
			product.setPrice(productModel.getPrice());
			product.setDescription(productModel.getDescription());
			product.setIsActive(true);
		} else {
			sessionFactory.getCurrentSession().persist(product);
		}
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void disableProduct(String productId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String hql = "UPDATE " + Product.class.getName() + " set isActive = 0 WHERE id = '" + productId + "'";
		Query query = session.createQuery(hql);
		query.executeUpdate();
	}

}
