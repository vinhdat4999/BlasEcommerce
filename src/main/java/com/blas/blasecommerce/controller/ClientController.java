package com.blas.blasecommerce.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.blas.blasecommerce.dao.CartDAO;
import com.blas.blasecommerce.dao.OrderDAO;
import com.blas.blasecommerce.dao.ProductDAO;
import com.blas.blasecommerce.dao.ProductImageDAO;
import com.blas.blasecommerce.dao.UserDAO;
import com.blas.blasecommerce.model.PaginationResult;
import com.blas.blasecommerce.model.ProductImageModel;
import com.blas.blasecommerce.model.ProductModel;

@Controller
@Transactional
@EnableWebMvc
public class ClientController {
	@Autowired
	private CartDAO cartDAO;

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private ProductImageDAO productImageDAO;

	@RequestMapping("/")
	public String listProductHandler(@CookieValue(value = "receiverInfo", defaultValue = "") String receiverInfoId,
			HttpServletResponse response, Model model, //
			@RequestParam(value = "name", defaultValue = "") String likeName,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "sort", defaultValue = "") String type) {
		String username = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		if (username.equals("anonymousUser")) {
			Cookie cookie = new Cookie("receiverInfo", null);
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
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

	@RequestMapping(value = { "/product" }, method = RequestMethod.GET)
	public String product(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam("id") String id) throws IOException {
		ProductModel productModel = null;
		if (id != null) {
			productModel = this.productDAO.findProductModel(id);
			if(productModel==null || !productModel.isActive()) {
				model.addAttribute("productNotFound", "true");
				return "product";
			}
		}
		if (productModel != null) {
			String priceStr = String.format("%,d", (int) productModel.getPrice());
			List<ProductImageModel> list = productImageDAO.getImageIdList(id);
			model.addAttribute("list", list);
			model.addAttribute("price", priceStr);
			model.addAttribute("productInfo", productModel);
		}
		return "product";
	}

	@RequestMapping(value = { "/product" }, method = RequestMethod.POST)
	public String buyProduct(HttpServletRequest request, Model model,
			@RequestParam(value = "id", defaultValue = "") String id) {

		String username = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();

		} else {
			username = principal.toString();
		}
		int quanity = 0;
		if (request.getParameter("quanityItem") == null) {
			quanity = 1;
		} else {
			quanity = Integer.parseInt(request.getParameter("quanityItem"));
		}
		cartDAO.updateItemInCart(id, quanity, username);
		return "redirect:/cart";
	}

	@RequestMapping({ "/shoppingCartRemoveProduct" })
	public String removeProductHandler(HttpServletRequest request, Model model, //
			@RequestParam(value = "id", defaultValue = "") String id) {
		cartDAO.deleteItemInCart(id);
		return "redirect:/cart";
	}

	public static boolean isStringIsDoubleNumber(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isStringIsLongNumber(String s) {
		try {
			Long.parseLong(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
