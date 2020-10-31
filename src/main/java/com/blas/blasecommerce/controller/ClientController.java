package com.blas.blasecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.blas.blasecommerce.dao.ProductDAO;
import com.blas.blasecommerce.dao.UserDAO;
import com.blas.blasecommerce.model.PaginationResult;
import com.blas.blasecommerce.model.ProductModel;

@Controller
@Transactional
@EnableWebMvc
public class ClientController {
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping("/")
	public String listProductHandler(Model model, //
			@RequestParam(value = "name", defaultValue = "") String likeName,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "sort", defaultValue = "") String type) {
		final int maxResult = 48;
		final int maxNavigationPage = 10;
		PaginationResult<ProductModel> result;
		if (type.equals("price-inc")) {
			result = productDAO.searchProductSortPrice(page, //
					maxResult, maxNavigationPage, likeName, "asc");
		} else {
			if (type.equals("price-des")) {
				result = productDAO.searchProductSortPrice(page, //
						maxResult, maxNavigationPage, likeName, "desc");
			} else {
				result = productDAO.searchProduct(page, //
						maxResult, maxNavigationPage, likeName);
			}
		}
		model.addAttribute("paginationProducts", result);
		return "productList";
	}
}
