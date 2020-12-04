package com.clubjuegos.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyErrorController implements ErrorController{

	@RequestMapping("/error")
	public String error(HttpServletRequest request, Model model){
		
		int codigo = (Integer) request.getAttribute("javax.servlet.error.status_code");
		
		model.addAttribute("codigo", codigo);
		
		return "Error";
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
	
	
}
