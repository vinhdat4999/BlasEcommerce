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
import com.blas.blasecommerce.dao.ReceiverInfoDAO;
import com.blas.blasecommerce.dao.UserDAO;
import com.blas.blasecommerce.entity.Product;
import com.blas.blasecommerce.model.CartDetailModel;
import com.blas.blasecommerce.model.CartModel;
import com.blas.blasecommerce.model.OrderDetailModel;
import com.blas.blasecommerce.model.OrderModel;
import com.blas.blasecommerce.model.PaginationResult;
import com.blas.blasecommerce.model.ProductModel;
import com.blas.blasecommerce.model.ReceiverInfoModel;
import com.blas.blasecommerce.model.UserModel;

@Controller
@Transactional
@EnableWebMvc
public class SystemController {

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private CartDAO cartDAO;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private OrderDAO orderDAO;
	
	@Autowired
	private ReceiverInfoDAO receiverInfoDAO;

	@RequestMapping("/403")
	public String accessDenied() {
		return "/403";
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}

	@RequestMapping(value = { "/accountInfo" }, method = RequestMethod.GET)
	public String accountInfo(Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserModel userModel = userDAO.findUserModel(userDetails.getUsername());
		model.addAttribute("user", userModel);
		return "accountInfo";
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
		double total = cartDAO.getTotalAmount(username);
		String totalStr = String.format("%,d", (int) total);
		
		ReceiverInfoModel receiverInfoModel = receiverInfoDAO.findReceiverInfoModelByUsername(username);
		model.addAttribute("receiverInfo", receiverInfoModel);
		model.addAttribute("total", totalStr);
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
		String[] quantityString = request.getParameterValues("quantityItem");
		int[] quantityInt = new int[quantityString.length];
		for (int i = 0; i < quantityString.length; i++) {
			int temp = Integer.parseInt(quantityString[i]);
			quantityInt[i] = temp;
		}
		cartDAO.updateQuantityInCart(quantityInt, username);
		return "redirect:/cart";
	}

	@RequestMapping(value = { "/orderList" }, method = RequestMethod.GET)
	public String orderList(Model model, //
			@RequestParam(value = "page", defaultValue = "1") String pageStr) {
		int page = 1;
		try {
			page = Integer.parseInt(pageStr);
		} catch (Exception e) {
		}
		final int MAX_RESULT = 5;
		final int MAX_NAVIGATION_PAGE = 10;

		String username = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		PaginationResult<OrderModel> paginationResult //
				= orderDAO.listOrderModelByUser(page, MAX_RESULT, MAX_NAVIGATION_PAGE, username);
		model.addAttribute("paginationResult", paginationResult);
		return "orderList";
	}

	@RequestMapping(value = { "/shoppingCartFinalize" }, method = RequestMethod.GET)
	public String shoppingCartFinalize(HttpServletRequest request, Model model) {

		String username = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		List<CartModel> itemList = cartDAO.getAllItemInCartByUser(username);
		if (itemList.size() == 0) {
			return "redirect:/shoppingCart";
		}
		cartDAO.deleteAllItemsInCartByUser(username);
		return "shoppingCartFinalize";
	}

	@RequestMapping(value = { "/order" }, method = RequestMethod.GET)
	public String orderView(Model model, @RequestParam("id") String id) {
		OrderModel orderModel = null;
		if (id != null) {
			orderModel = this.orderDAO.getOrderModel(id);
		}
		if (orderModel == null) {
			return "redirect:/orderList";
		}
		List<OrderDetailModel> details = orderDAO.listOrderDetailModels(id);
		if (details == null) {
			return "redirect:/orderList";
		}
		model.addAttribute("orderInfo", orderModel);
		model.addAttribute("detailList", details);
		return "order";
	}

	@RequestMapping(value = { "/incItem" }, method = RequestMethod.GET)
	public String incItem(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam("id") String id) {
		CartModel cartModel = cartDAO.findCartModel(id);
		cartDAO.updateQuantityItemInCart(id, cartModel.getQuantity() + 1);
		return "redirect:/cart";
	}

	@RequestMapping(value = { "/desItem" }, method = RequestMethod.GET)
	public String desItem(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam("id") String id) {
		CartModel cartModel = cartDAO.findCartModel(id);
		if(cartModel.getQuantity()>=2) {
			cartDAO.updateQuantityItemInCart(id, cartModel.getQuantity() - 1);	
		}
		return "redirect:/cart";
	}
	
//	@RequestMapping(value = { "/desItem" }, method = RequestMethod.GET)
//	public String inputItem(HttpServletRequest request, HttpServletResponse response, Model model,
//			@RequestParam("id") String id) {
//		CartModel cartModel = cartDAO.findCartModel(id);
//		if(cartModel.getQuantity()>=2) {
//			cartDAO.updateQuantityItemInCart(id, cartModel.getQuantity() - 1);	
//		}
//		return "redirect:/cart";
//	}
}
