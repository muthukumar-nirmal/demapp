/**
 * 
 */
package com.demapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class DashboardController {
	
	@Autowired
	UserService userService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DashboardController.class);

	@RequestMapping(value="/admin/adminHome", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
		LOGGER.info("Reading username from the user object by passing username {} : " + user.getEmail());
		modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
		modelAndView.setViewName("authorized/admin/home");
		return modelAndView;
	}
	
	@RequestMapping(value="/user/userHome", method = RequestMethod.GET)
	public ModelAndView userHome(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		//modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		LOGGER.info("Reading username from the user object by passing username {} : " + user.getEmail());
		modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
		modelAndView.addObject("userMessage","Content Available Only for Users with User Role");
		modelAndView.setViewName("authorized/user/home");
		return modelAndView;
	}
}
