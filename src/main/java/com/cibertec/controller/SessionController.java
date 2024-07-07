package com.cibertec.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpSession;

@ControllerAdvice
public class SessionController {
	 @ModelAttribute
	    public void addAttributes(Model model, HttpSession session) {
	        Object nombreActivo = session.getAttribute("nombreActivo");
	        Object rolActivo = session.getAttribute("rolActivo");

	        if (nombreActivo != null && rolActivo != null) {
	            model.addAttribute("nombreActivo", nombreActivo);
	            model.addAttribute("rolActivo", rolActivo);
	        }
	    }
}
