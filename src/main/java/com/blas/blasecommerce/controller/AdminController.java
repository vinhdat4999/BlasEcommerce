package com.blas.blasecommerce.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.blas.blasecommerce.dao.CartDAO;
import com.blas.blasecommerce.dao.CategoryDAO;
import com.blas.blasecommerce.dao.ProductDAO;
import com.blas.blasecommerce.dao.ProductImageDAO;
import com.blas.blasecommerce.entity.Category;
import com.blas.blasecommerce.entity.ProductImage;
import com.blas.blasecommerce.model.CategoryModel;
import com.blas.blasecommerce.model.PaginationResult;
import com.blas.blasecommerce.model.ProductImageModel;
import com.blas.blasecommerce.model.ProductModel;
import com.blas.blasecommerce.model.UploadImage;

@Controller
@Transactional
@EnableWebMvc
public class AdminController {

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private ProductImageDAO productImageDAO;
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private CartDAO cartDAO;

	@RequestMapping(value = { "/createProduct" }, method = RequestMethod.GET)
	public String createProduct(Model model) {
		model.addAttribute("productId", UUID.randomUUID().toString());
		model.addAttribute("categoryList",categoryDAO.getCategoryList());
		UploadImage myUploadForm = new UploadImage();
		model.addAttribute("myUploadForm", myUploadForm);
		return "createProduct";
	}

	@RequestMapping(value = { "/createProduct" }, method = RequestMethod.POST)
	@Transactional(propagation = Propagation.NEVER)
	public String productSaveNew(HttpServletRequest request, Model model, //
			@ModelAttribute("myUploadForm") @Validated ProductModel productModel, UploadImage uploadImage) {
		System.out.println("product: " + productModel.toString());
		boolean hasError = false;
		if (productModel.getName().trim().length() == 0) {
			hasError = true;
			model.addAttribute("nameError", "Please input product name");
		}
		String priceSt = request.getParameter("priceSt").toString();
		if (priceSt == null || priceSt.length() == 0) {
			hasError = true;
			model.addAttribute("priceError", "Please input price");
		} else {
			if (!isStringIsDoubleNumber(priceSt)) {
				hasError = true;
				model.addAttribute("priceError", "Please input price include number");
			}
		}
		if (hasError) {
			model.addAttribute("productId", UUID.randomUUID().toString());
			return "createProduct";
		} else {
			LocalDate localDate = java.time.LocalDate.now();
			Date date = new Date(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());
			ProductModel productModel2 = new ProductModel(productModel.getId(), productModel.getCategory(), date, productModel.getName(), productModel.getPrice(),productModel.getDescription(),productModel.isActive()); 
			productModel2.setImage(productModel.getImage());
			productModel2.setPrice(Double.parseDouble(priceSt));
			productModel2.setActive(true);
			productDAO.save(productModel2);


			CommonsMultipartFile[] fileDatas = uploadImage.getFileDatas();
			for (CommonsMultipartFile fileData : fileDatas) {
				if (fileData.getSize() != 0) {
					ProductImage temp = new ProductImage(UUID.randomUUID().toString(), productModel2.getId(), fileData.getBytes());
					productImageDAO.addImage(temp);
				}
			}
			return "redirect:/";
		}
	}

	@RequestMapping(value = { "/editProduct" }, method = RequestMethod.GET)
	public String product(Model model, @RequestParam(value = "id", defaultValue = "") String id) {
		ProductModel productModel = null;
		if (id != null && id.length() > 0) {
			productModel = productDAO.findProductModel(id);
			List<ProductImageModel> listLink = productImageDAO.getImageIdList(id);
			model.addAttribute("listLink", listLink);
			model.addAttribute("priceSt", productModel.getPrice() + "");
			model.addAttribute("productForm", productModel);
			
			UploadImage myUploadForm = new UploadImage();
			model.addAttribute("myUploadForm", myUploadForm);
			List<CategoryModel> categoryList = categoryDAO.getCategoryList();
			model.addAttribute("categoryList", categoryList);
			
			PaginationResult<ProductImageModel> result = productImageDAO.queryProductImages(1, 1000, 1000,"");
			model.addAttribute("paginationProductImage", result);
		}
		return "editProduct";
	}

	@RequestMapping(value = { "/editProduct" }, method = RequestMethod.POST)
	@Transactional(propagation = Propagation.NEVER)
	public String productSave(HttpServletRequest request, Model model, //
			@ModelAttribute("myUploadForm") @Validated ProductModel productModel, UploadImage uploadImage) {
		boolean hasError = false;
		ProductModel productModel2 = productDAO.findProductModel(productModel.getId());
		if (productModel.getName().trim().length() == 0) {
			hasError = true;
			model.addAttribute("nameError", "Please input product name");
		}
		String priceSt = request.getParameter("priceSt").trim();
		if (priceSt.length() == 0) {
			hasError = true;
			model.addAttribute("priceError", "Please input price");
		} else {
			if (!isStringIsDoubleNumber(priceSt)) {
				hasError = true;
				model.addAttribute("priceError", "Please input price include number");
			}
		}
		if (hasError) {
			model.addAttribute("priceSt", productModel2.getPrice() + "");
			return "editProduct";
		} else {
			productModel2.setName(productModel.getName());
			productModel2.setCategory(productModel.getCategory());
			productModel2.setPrice(Double.parseDouble(priceSt));
			productModel2.setImage(productModel.getImage());
			productModel2.setDescription(productModel.getDescription());
//			productModel2.setActive(true);
			productDAO.save(productModel2);
			
			//save sub image
			String productId = request.getParameter("productId");
			List<ProductImageModel> list = productImageDAO.getImageIdList(productId);

			CommonsMultipartFile[] fileDatas = uploadImage.getFileDatas();
			int i = 0; 
			for (CommonsMultipartFile fileData : fileDatas) {
				i++;
				if (fileData.getSize() != 0) {
					if(i<fileDatas.length) {
						productImageDAO.deleteImage(list.get(i-1).getId());	
					}
					ProductImage temp = new ProductImage(UUID.randomUUID().toString(), productId, fileData.getBytes());
					productImageDAO.addImage(temp);
					if(i==fileDatas.length) {
						return "redirect:/editProduct?id=" + productId;
					}
				}
			}
		}
		return "redirect:/";
	}

	@RequestMapping("/delete-image")
	public String deleteImage(Model model, //
			@RequestParam(value = "imageId", defaultValue = "") String imageId) {
		ProductImageModel productImageModel = productImageDAO.findProductImageModel(imageId);
		productImageDAO.deleteImage(imageId);
		return "redirect:/editProduct?id=" + productImageModel.getProductId();
	}

	@RequestMapping("/delete-product")
	public String deleteProduct(Model model, //
			@RequestParam(value = "productId", defaultValue = "") String productId) {
		productDAO.disableProduct(productId);
		cartDAO.deleteAllItemInCartByProduct(productId);
		return "redirect:/";
	}

	public static boolean isStringIsDoubleNumber(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
