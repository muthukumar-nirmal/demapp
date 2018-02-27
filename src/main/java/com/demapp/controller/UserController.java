/**
 * 
 */
package com.demapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.demapp.service.UserService;

/**
 * @author muthu_m
 *
 */
@Controller
public class UserController 
{
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@GetMapping(value="/admin/user")
	public ModelAndView listUser()
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("userName", userService.getCurrentUser().getName() + " " + userService.getCurrentUser().getLastName());
		LOGGER.info("Reading username from the user object by passing username {} : " + userService.getCurrentUser().getEmail());
		modelAndView.addObject("users", userService.listUsers());
		modelAndView.setViewName("authorized/admin/user");
		return modelAndView;
	}

}
