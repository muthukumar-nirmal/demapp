/**
 * 
 */
package com.demapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.demapp.model.User;
import com.demapp.service.UserService;

/**
 * @author muthu_m
 *
 */
@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value="/admin/user", method = RequestMethod.GET)
	public ModelAndView listUser()
	{
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
		modelAndView.addObject("users", userService.listUsers());
		modelAndView.setViewName("authorized/admin/user");
		return modelAndView;
	}
}
