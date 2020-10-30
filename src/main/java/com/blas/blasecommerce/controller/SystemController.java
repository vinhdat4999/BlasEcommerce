package com.blas.blasecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@Transactional
@EnableWebMvc
public class SystemController {

	@RequestMapping("/403")
	public String accessDenied() {
		return "/403";
	}
}
