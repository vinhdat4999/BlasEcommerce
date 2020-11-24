package com.blas.blasecommerce.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.blas.blasecommerce.dao.AuthenticationDAO;
import com.blas.blasecommerce.dao.CartDAO;
import com.blas.blasecommerce.dao.OrderDAO;
import com.blas.blasecommerce.dao.ProductDAO;
import com.blas.blasecommerce.dao.ProductImageDAO;
import com.blas.blasecommerce.dao.ReceiverInfoDAO;
import com.blas.blasecommerce.dao.UserDAO;
import com.blas.blasecommerce.entity.Authentication;
import com.blas.blasecommerce.entity.Product;
import com.blas.blasecommerce.entity.ProductImage;
import com.blas.blasecommerce.entity.ReceiverInfo;
import com.blas.blasecommerce.entity.User;
import com.blas.blasecommerce.model.CartDetailModel;
import com.blas.blasecommerce.model.CartModel;
import com.blas.blasecommerce.model.OrderDetailModel;
import com.blas.blasecommerce.model.OrderModel;
import com.blas.blasecommerce.model.PaginationResult;
import com.blas.blasecommerce.model.ProductModel;
import com.blas.blasecommerce.model.ReceiverInfoModel;
import com.blas.blasecommerce.model.UserModel;
import com.blas.blasecommerce.util.SendEmail;

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

	@Autowired
	private AuthenticationDAO authenticationDAO;

	@Autowired
	private ProductImageDAO productImageDAO;

	@RequestMapping("/403")
	public String accessDenied() {
		return "/403";
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String login(Model model) {
		String username = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		if (!username.equals("anonymousUser")) {
			return "redirect:/";
		}
		return "login";
	}

//	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
//	public String login(Model model, HttpServletRequest request) {
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
//		try {
//			password = new Encrypt().encrypt(password);
//		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		if(userDAO.isValidUser(username, password)) {
//			return "redirect:/";
//		}
//		return "redirect:/login";
//	}

	@RequestMapping(value = { "/accountInfo" }, method = RequestMethod.GET)
	public String accountInfo(Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserModel userModel = userDAO.findUserModel(userDetails.getUsername());
		String username = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();

		}
		final int maxResult = 1000;
		final int maxNavigationPage = 1000;
		PaginationResult<ReceiverInfoModel> result = receiverInfoDAO.queryReceiverInfos(1, //
				maxResult, maxNavigationPage, username);
		model.addAttribute("paginationReceiverInfos", result);

		model.addAttribute("user", userModel);
		return "accountInfo";
	}

	@RequestMapping(value = { "/image" }, method = RequestMethod.GET)
	public void image(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam("id") String imageId) throws IOException {
		ProductImage productImage = null;
		if (imageId != null) {
			productImage = productImageDAO.findProductImage(imageId);
		}
		if (productImage != null && productImage.getImage() != null) {
			response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
			response.getOutputStream().write(productImage.getImage());
		}
		response.getOutputStream().close();
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
	public String shoppingCartHandler(@CookieValue(value = "receiverInfo", defaultValue = "") String receiverInfoId,
			HttpServletRequest request, HttpServletResponse response, Model model) {
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

		ReceiverInfoModel receiverInfoModel = null;
		if (receiverInfoId == null || receiverInfoId.equals("")) {
			receiverInfoModel = receiverInfoDAO.findReceiverInfoModelByUsername(username);
			Cookie cookie = new Cookie("receiverInfo", receiverInfoModel.getId());
			response.addCookie(cookie);
		} else {
			receiverInfoModel = receiverInfoDAO.findReceiverInfoModelById(receiverInfoId);
		}
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
		model.addAttribute("pageNow", pageStr);
		return "orderList";
	}

	@RequestMapping(value = { "/order" }, method = RequestMethod.GET)
	public String orderView(Model model, @RequestParam("id") String id) {
		String username = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		OrderModel orderModel = null;
		if (id != null) {
			orderModel = this.orderDAO.getOrderModel(id);
		}
		if (orderModel == null) {
			return "redirect:/orderList";
		}
		if (!orderModel.getUsername().equals(username)) {
			return "403";
		}
		List<OrderDetailModel> details = orderDAO.listOrderDetailModels(id);
		for (OrderDetailModel i : details) {
			ProductModel productModel = productDAO.findProductModel(i.getProductId());
			i.setName(productModel.getName());
		}
		if (details == null) {
			return "redirect:/orderList";
		}
		ReceiverInfoModel receiverInfoModel = receiverInfoDAO.findReceiverInfoModelById(orderModel.getReceiverInfoId());
		double total = orderModel.getTotal();
		String totalStr = String.format("%,d", (int) total);
		model.addAttribute("receiverInfo", receiverInfoModel);
		model.addAttribute("orderInfo", orderModel);
		model.addAttribute("detailList", details);
		model.addAttribute("total", totalStr);
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
		if (cartModel.getQuantity() >= 2) {
			cartDAO.updateQuantityItemInCart(id, cartModel.getQuantity() - 1);
		}
		return "redirect:/cart";
	}

	@RequestMapping(value = { "/shipping" }, method = RequestMethod.GET)
	public String chooseReceiverInfo(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "page", defaultValue = "1") int page) {
		String username = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		final int maxResult = 48;
		final int maxNavigationPage = 10;
		PaginationResult<ReceiverInfoModel> result = receiverInfoDAO.queryReceiverInfos(page, //
				maxResult, maxNavigationPage, username);
		model.addAttribute("paginationReceiverInfos", result);
		return "shipping";
	}

	@RequestMapping(value = { "/shipping" }, method = RequestMethod.POST)
	public String createReceiverInfo(HttpServletRequest request, HttpServletResponse response,
			Model model, @ModelAttribute("receiverInfo") @Validated ReceiverInfoModel receiverInfoModel) {
		String username = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		String id = UUID.randomUUID().toString();
		ReceiverInfoModel receiverInfoModel2 = new ReceiverInfoModel(id, username, receiverInfoModel.getReceiverName(),
				receiverInfoModel.getReceiverPhone(), receiverInfoModel.getReceiverEmail(),
				receiverInfoModel.getReceiverAddress());
		receiverInfoDAO.save(receiverInfoModel2);
		Cookie cookie = new Cookie("receiverInfo", id);
		response.addCookie(cookie);
		return "redirect:/cart";
	}

	@RequestMapping(value = { "/shipping-to" }, method = RequestMethod.GET)
	public String changeReceiverInfo(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "receiverInfo", defaultValue = "") String receiverInfoId) {
		String username = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		ReceiverInfoModel receiverInfoModel = receiverInfoDAO.findReceiverInfoModelById(receiverInfoId);
		if (!receiverInfoModel.getUsername().equals(username)) {
			return "redirect:/shipping";
		}
		Cookie cookie = new Cookie("receiverInfo", receiverInfoId);
		response.addCookie(cookie);
		return "redirect:/cart";
	}

	@RequestMapping(value = { "/checkout" }, method = RequestMethod.GET)
	public String checkout(@CookieValue(value = "receiverInfo", defaultValue = "") String receiverInfoId,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		ReceiverInfoModel receiverInfoModel = receiverInfoDAO.findReceiverInfoModelById(receiverInfoId);
		String username = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		double total = cartDAO.getTotalAmount(username);
		String totalStr = String.format("%,d", (int) total);
		model.addAttribute("total", totalStr);
		model.addAttribute("receiverInfo", receiverInfoModel);
		return "checkout";
	}

	@RequestMapping(value = { "/checkout" }, method = RequestMethod.POST)
	public String checkout(@CookieValue(value = "receiverInfo", defaultValue = "") String receiverInfoId, Model model,
			HttpServletResponse response) {
		String username = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		String orderId = orderDAO.saveOrder(username, receiverInfoId);
		Cookie cookie = new Cookie("receiverInfo", null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		Cookie cookie2 = new Cookie("orderId", orderId);
		response.addCookie(cookie2);
		return "redirect:/shoppingCartFinalize";
	}

	@RequestMapping(value = { "/shoppingCartFinalize" }, method = RequestMethod.GET)
	public String shoppingCartFinalize(@CookieValue(value = "orderId", defaultValue = "") String orderId,
			HttpServletRequest request, Model model, HttpServletResponse response) {
		if (orderId == null || orderId.equals("")) {
			return "redirect:/";
		}
		String username = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		UserModel user = userDAO.findUserModel(username);
		OrderModel orderModel = orderDAO.getOrderModel(orderId);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		String formatDateTime = orderModel.getOrderTime().format(formatter);
		ReceiverInfoModel receiverInfoModel = receiverInfoDAO.findReceiverInfoModelById(orderModel.getReceiverInfoId());
		String content = "<!DOCTYPE html>\n" + "<html>\n" + "<head>\n" + "<meta charset=\"UTF-8\">\n" + "\n"
				+ "<link href=\"https://fonts.googleapis.com/css?family=Open+Sans:400,700\"\n"
				+ "	rel=\"stylesheet\">\n" + "<link rel=\"stylesheet\" type=\"text/css\"\n"
				+ "	href=\"${pageContext.request.contextPath}/styles.css\">\n" + "<style>\n" + "\n" + "body {\n"
				+ "	background-color: #8080801a;\n" + "	font-family: 'open sans';\n" + "	overflow-x: hidden;\n"
				+ "}\n" + ".btnDetail {\n" + "	text-decoration: none;\n" + "	background: #189fff;\n"
				+ "	padding: 0.8em 0.8em;\n" + "	border: none;\n" + "	text-transform: UPPERCASE;\n"
				+ "	font-weight: bold;\n" + "	color: #fff;\n" + "	-webkit-transition: background .3s ease;\n"
				+ "	transition: background .3s ease;\n" + "}\n" + "\n" + ".btnDetail:hover {\n"
				+ "	background: #18cfff;\n" + "	color: #fff;\n" + "	cursor: pointer;\n" + "}" + "</style>\n"
				+ "</head>\n" + "<body>\n" + "    <div>\n" + "        <div>\n" + "            <h3>Cảm ơn quý khách "
				+ user.getFirstname() + " " + user.getLastname() + " đã đặt hàng tại BLAS,</h3>\n"
				+ "            <p> BLAS rất vui thông báo đơn hàng #" + orderId
				+ "			   của quý khách đã được tiếp nhận và đang trong quá trình xử lý. BLAS sẽ thông báo đến quý khách ngay khi hàng chuẩn bị được giao.</br></br></p>\n"
				+ "        </div>\n" + "        <div>\n" + "            <div style=\"display: flex;\">\n"
				+ "                <h3 style=\"color:#22a2ff;\">THÔNG TIN ĐƠN HÀNG #" + orderId + "</h3>"
				+ "                <h4 style=\"color:#94a3ad; margin-left: 10px;\">(Thời gian đặt hàng: "
				+ formatDateTime + ")</h4>\n" + "            </div>\n"
				+ "            <div style=\"margin-left: 5%;\">\n"
				+ "                <p style=\"font-weight: bold;\">Địa chỉ giao hàng</p>\n" + "                <div>"
				+ receiverInfoModel.getReceiverName() + "</div>\n" + "                <div>"
				+ receiverInfoModel.getReceiverAddress() + "</div>\n" + "                <div>"
				+ receiverInfoModel.getReceiverPhone() + "</div>\n" + "            </div>\n"
				+ "            <div style=\"margin-top: 15px;\">Phương thức thanh toán: Thanh toán tiền mặt khi nhận hàng</div>\n"
				+ "            <div style=\"font-style: italic;\">Lưu ý: Đối với đơn hàng đã được thanh toán trước, nhân viên giao nhận có thể yêu cầu người nhận hàng cung cấp CMND / giấy phép lái xe để chụp ảnh hoặc ghi lại thông tin.</div>\n"
				+ "        </div>\n" + "			<div style=\"margin-top: 20px; width: 60%;margin-left: 19%;\">\n"
				+ "            <div>\n" + "                <div\n"
				+ "                    style=\"padding-left: 40px; padding-right: 40px; display: flex; font-weight: bold; background-color: #22a2ff; color: white;\">\n"
				+ "                    <div style=\"width: 60%;\">Sản phẩm</div>\n"
				+ "                    <div style=\"width: 14%;\">Giá</div>\n"
				+ "                    <div style=\"width: 12%;\">Số lượng</div>\n"
				+ "                    <div style=\"width: 14%;\">Tạm tính</div>\n" + "                </div>\n"
				+ "                <div style=\"padding: 40px; padding-top: inherit; background-color: #d7e2e9;\">\n";
		List<CartModel> itemList = cartDAO.getAllItemInCartByUser(username);
		for (CartModel i : itemList) {
			ProductModel productModel = productDAO.findProductModel(i.getProductId());
			content += "<div class=\"product-preview-shopping-cart-container\"\n"
					+ "                            style=\"display: flex;\">\n"
					+ "                            <div style=\"width: 60%;\">\n" + productModel.getName()
					+ "                            </div>\n" + "                            <div style=\"width: 14%;\">"
					+ productModel.getPrice() + "</div>\n" + "                            <div style=\"width: 12%;\">"
					+ i.getQuantity() + "</div>\n" + "                            <div style=\"width: 14%;\">"
					+ (i.getQuantity() * productModel.getPrice()) + "</div>\n" + "                        </div>";
		}
		content += "                    <div style=\"width: 100%;margin-top: 25px; margin-left: 50%; font-weight: bold;\">TỔNG GIÁ TRỊ ĐƠN HÀNG: "
				+ cartDAO.getTotalAmount(username) + "đ </div> \n" + "                </div>\n"
				+ "            </div>            \n" + "        </div>\n"
				+ "		   <div style=\"margin-top: 30px; margin-left: 40%;\">"
				+ "            <a class=\"btnDetail\" href=\"http://localhost:8080/BlasEcommerce/order?id=" + orderId
				+ "\">Xem chi tiết đơn hàng</a>\n" + "        </div>" + "        <div style=\"margin-top: 20px;\">\n"
				+ "            Mọi thắc mắc và góp ý, quý khách vui lòng liên hệ với BLAS Care qua <a href=\"https://www.facebook.com/vinhdat4999/\">Facebook</a> hoặc hotline 0965 040 999. Đội ngũ BLAS Care luôn sẵn sàng hỗ trợ bạn.\n"
				+ "        </div>\n" + "        <div style=\"font-weight: bold;\">"
				+ "            Một lần nữa BLAS cảm ơn quý khách.\n" + "        </div>     \n"
				+ "        <div style=\"color: #22a2ff; margin-left: 80%;font-weight: bold;\">\n"
				+ "            <h3>BLAS</h3>\n" + "        </div> " + "    </div>\n" + "</body>\n" + "</html>";
		String title = "Xác nhận đơn hàng #" + orderId + " BLAS\n";
		UserModel userModel = userDAO.findUserModel(username);
		new SendEmail().sendEmail(userModel.getEmail(), title, content);
		Cookie cookie = new Cookie("orderId", null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		if (itemList.size() == 0) {
			return "redirect:/shoppingCart";
		}
		cartDAO.deleteAllItemsInCartByUser(username);
		return "shoppingCartFinalize";
	}

	@RequestMapping(value = { "/place-again" }, method = RequestMethod.GET)
	public String placeAgain(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "id", defaultValue = "") String orderId) {
		OrderModel orderModel = orderDAO.getOrderModel(orderId);
		String username = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		if (!username.equals(orderModel.getUsername())) {
			return "redirect:/";
		}
		List<OrderDetailModel> list = orderDAO.listOrderDetailModels(orderId);
		cartDAO.deleteAllItemsInCartByUser(username);
		for (OrderDetailModel i : list) {
			cartDAO.updateItemInCart(i.getProductId(), i.getQuantity(), orderModel.getUsername());
		}
		return "redirect:/cart";
	}

	@RequestMapping(value = { "/signup" }, method = RequestMethod.GET)
	public String signup(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		return "signup";
	}

	@RequestMapping(value = { "/signup" }, method = RequestMethod.POST)
	@Transactional(propagation = Propagation.NEVER)
	public String signup(HttpServletRequest request, Model model, //
			@ModelAttribute("accountForm") @Validated UserModel userModel) {
		String[] nameSplit = userModel.getLastname().split(" ");
		String lastname = nameSplit[nameSplit.length - 1];
		String firstname = "";
		for (int i = 0; i < nameSplit.length - 1; i++) {
			firstname += nameSplit[i] + " ";
		}
		userModel.setFirstname(firstname.trim());
		userModel.setLastname(lastname);
		String radioValue = request.getParameter("genderR");
		if (radioValue.equals("Nam")) {
			userModel.setGender(true);
		} else {
			userModel.setGender(false);
		}
		String day = request.getParameter("day");
		String month = request.getParameter("month");
		String year = request.getParameter("year");
		Date date = new Date(Integer.parseInt(year) - 1900, Integer.parseInt(month) - 1, Integer.parseInt(day));
		userModel.setBirthdate(date);
		userModel.setActive(true);
		userModel.setUserRole("CUSTOMER");

		User user = new User(userModel);
		String address = request.getParameter("address");
		ReceiverInfoModel receiverInfoModel = new ReceiverInfoModel(UUID.randomUUID().toString(),
				userModel.getUsername(), userModel.getFirstname() + " " + userModel.getLastname(),
				userModel.getPhoneNum(), userModel.getEmail(), address);

		userDAO.saveUser(user, new ReceiverInfo(receiverInfoModel));
		return "redirect:/login";
	}

	@RequestMapping(value = { "/resetPassword" }, method = RequestMethod.GET)
	public String resetPassword(HttpServletRequest request, Model model) throws IOException {
		return "resetPassword";
	}

	@RequestMapping(value = { "/resetPassword" }, method = RequestMethod.POST)
	@Transactional(propagation = Propagation.NEVER)
	public String resetPassword(HttpServletRequest request, HttpServletResponse response, Model model) {
		String username = request.getParameter("username").trim();
		UserModel userModel = userDAO.findUserModel(username);
		Random random = new Random();
		int code = random.nextInt(900000) + 100000;
		authenticationDAO.save(username, code + "");
		String title = "Ä�áº·t láº¡i máº­t kháº©u tÃ i khoáº£n BLAS";
		String content = "Vui lÃ²ng khÃ´ng tiáº¿t lá»™ mÃ£ xÃ¡c thá»±c cho báº¥t kÃ¬ ai. MÃ£ xÃ¡c thá»±c sáº½ háº¿t háº¡n trong 20 phÃºt. MÃ£ xÃ¡c thá»±c tÃ i khoáº£n cá»§a báº¡n lÃ Â  : "
				+ code + ".";
		new SendEmail().sendEmail(userModel.getEmail(), title, content);
		Cookie cookie = new Cookie("resetPassUsername", username);
		response.addCookie(cookie);
		return "redirect:/new-password";
	}

	@RequestMapping(value = { "/new-password" }, method = RequestMethod.GET)
	public String newPassword(@CookieValue(value = "resetPassUsername", defaultValue = "") String username,
			HttpServletRequest request, Model model) throws IOException {
		UserModel userModel = userDAO.findUserModel(username);
		String email = userModel.getEmail();
		String[] temp = email.split("@");
		String temp2 = temp[0];
		email = temp2.substring(0, temp2.length() - 4).concat("****");
		email += "@" + temp[1];
		model.addAttribute("email", email);
		return "newPassword";
	}

	@RequestMapping(value = { "/new-password" }, method = RequestMethod.POST)
	@Transactional(propagation = Propagation.NEVER)
	public String newPassword(@CookieValue(value = "resetPassUsername", defaultValue = "") String username,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		String code = request.getParameter("code");
		String password = request.getParameter("retype");
		if (authenticationDAO.isValidAuthentication(username, code)) {
			UserModel userModel = userDAO.findUserModel(username);
			userModel.setPassword(password);
			userDAO.saveUser(new User(userModel));
			Cookie cookie = new Cookie("resetPassUsername", null);
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			Authentication authentication = authenticationDAO.findAuthentication(username);
			LocalDateTime expire = LocalDateTime.of(1900, 1, 1, 1, 1, 1);
			authentication.setTimeExpire(expire);
			authenticationDAO.save(username, expire);
		}
		return "redirect:/login";
	}
}
