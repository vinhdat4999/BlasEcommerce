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
import com.blas.blasecommerce.dao.OrderDAO;
import com.blas.blasecommerce.dao.ProductDAO;
import com.blas.blasecommerce.dao.UserDAO;
import com.blas.blasecommerce.model.CartDetailModel;
import com.blas.blasecommerce.model.CartModel;
import com.blas.blasecommerce.model.PaginationResult;
import com.blas.blasecommerce.model.ProductImageModel;
import com.blas.blasecommerce.model.ProductModel;
import com.blas.blasecommerce.model.UserModel;

@Controller
@Transactional
@EnableWebMvc
public class ClientController {
	@Autowired
	private CartDAO cartDAO;

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private OrderDAO orderDAO;

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

	@RequestMapping(value = { "/product" }, method = RequestMethod.GET)
	public String product(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam("id") String id) throws IOException {
		ProductModel productModel = null;
		if (id != null) {
			productModel = this.productDAO.findProductModel(id);
		}
		if (productModel != null) {
			String priceStr = String.format("%,d", (int) productModel.getPrice());
//			List<ProductImageModel> listLink = productImageDAO.getListLinkImage(id);
//			if (listLink.size() > 0) {
//				listLink.remove(0);
//			}
//			model.addAttribute("listLink", listLink);
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

	@RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.GET)
	public String shoppingCartCustomerForm(HttpServletRequest request, Model model) {

		String username = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		List<CartModel> itemList = cartDAO.getAllItemInCartByUser(username);

		if (itemList.size() == 0) {
			return "redirect:/cart";
		}
		UserModel userModel = userDAO.findUserModel(username);
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
		model.addAttribute("customerForm", userModel);
		double totalAmount = cartDAO.getTotalAmount(username);
		List<Double> itemAmount = new ArrayList<Double>();
		itemAmount = cartDAO.getTotalAmountItem(username);
		model.addAttribute("totalAmount", totalAmount);
		model.addAttribute("itemAmount", itemAmount);
		return "shoppingCartCustomer";
	}

	@RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.POST)
	public String shoppingCartCustomerSave(HttpServletRequest request, //
			Model model, //
			@ModelAttribute("customerForm") @Validated UserModel customerForm) {
		boolean hasError = false;
		if (customerForm.getLastname().trim().length() == 0) {
			hasError = true;
			model.addAttribute("nameError", "Please input name");
		}
		if (customerForm.getPhoneNum().trim().length() == 0) {
			hasError = true;
			model.addAttribute("phoneError", "Please input phone number");
		} else {
			if (!isStringIsLongNumber(customerForm.getPhoneNum().trim())) {
				hasError = true;
				model.addAttribute("phoneError", "Please input phone number include number");
			}
		}
		if (customerForm.getEmail().trim().length() > 0) {
			String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
			if (!customerForm.getEmail().trim().matches(regex)) {
				hasError = true;
				model.addAttribute("emailError", "Please input email valid");
			}
		}
//		if (customerForm.getAddressId().length() == 0) {
//			hasError = true;
//			model.addAttribute("addressError", "Please input address");
//		}
		String username = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		if (hasError) {
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
			return "shoppingCartCustomer";
		} else {
			try {
				orderDAO.saveOrder(username, customerForm.getLastname(), customerForm.getPhoneNum(),
						customerForm.getEmail(), "a");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return "redirect:/shoppingCartFinalize";
		}
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
