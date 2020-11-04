package com.blas.blasecommerce.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.blas.blasecommerce.dao.CartDAO;
import com.blas.blasecommerce.dao.ProductDAO;
import com.blas.blasecommerce.entity.Product;
import com.blas.blasecommerce.model.CartDetailModel;
import com.blas.blasecommerce.model.CartModel;
import com.blas.blasecommerce.model.OrderDetailModel;
import com.blas.blasecommerce.model.PaginationResult;
import com.blas.blasecommerce.model.ProductModel;

@Controller
@Transactional
@EnableWebMvc
public class SystemController {
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private CartDAO cartDAO;
	
	@RequestMapping("/403")
	public String accessDenied() {
		return "/403";
	}
	
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}

	@RequestMapping(value = { "/productImage" }, method = RequestMethod.GET)
	public void productImage(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam("id") String id) throws IOException {
		Product product = null;
		if (id != null) {
			product = productDAO.findProduct(id);
		}
		if (product != null && product.getImage() != null) {
			response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
			response.getOutputStream().write(product.getImage());
		}
		response.getOutputStream().close();
	}

	@RequestMapping({ "/search" })
	public String searchItem(HttpServletRequest request, Model model, //
			@RequestParam(value = "content", defaultValue = "") String content,
			@RequestParam(value = "sort", defaultValue = "") String type) {
		final int maxResult = 48;
		final int maxNavigationPage = 10;
		PaginationResult<ProductModel> result;
		if (type.equals("price-inc")) {
			result = productDAO.searchProductSortPrice(1, //
					maxResult, maxNavigationPage, content, "asc");
		} else {
			if (type.equals("price-des")) {
				result = productDAO.searchProductSortPrice(1, //
						maxResult, maxNavigationPage, content, "desc");
			} else {
				result = productDAO.searchProduct(1, //
						maxResult, maxNavigationPage, content);
			}
		}
		model.addAttribute("paginationProducts", result);
		model.addAttribute("searchContent", content);
		return "productList";
	}
	
	@RequestMapping(value = { "/cart" }, method = RequestMethod.GET)
	public String shoppingCartHandler(HttpServletRequest request, Model model) {
		String username = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		List<CartModel> itemList = cartDAO.getAllItemInCartByUser(username);
		List<CartDetailModel> detailList = new ArrayList<CartDetailModel>();
		for (CartModel i : itemList) {
			CartDetailModel temp = new CartDetailModel(i.getId(), i.getProductId(), "", i.getQuantity(), 0,
					i.getUsername());
			ProductModel productModel = productDAO.findProductModel(i.getProductId());
			temp.setProductName(productModel.getName());
			temp.setPrice(productModel.getPrice());
			detailList.add(temp);
		}
		model.addAttribute("detailList", detailList);
		return "shoppingCart";
	}

	@RequestMapping(value = { "/cart" }, method = RequestMethod.POST)
	public String shoppingCartUpdateQty(HttpServletRequest request, //
			Model model, @ModelAttribute("detailList") @Validated CartDetailModel detailList) {
		String username = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		String[] quantityString = request.getParameterValues("quantity");
		int[] quantityInt = new int[quantityString.length];
		for (int i = 0; i < quantityString.length; i++) {
			int temp = Integer.parseInt(quantityString[i]);
			quantityInt[i] = temp;
		}
		cartDAO.updateQuantityInCart(quantityInt, username);
		return "redirect:/shoppingCart";
	}
}
