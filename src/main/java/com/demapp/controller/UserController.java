/**
 * 
 */
package com.demapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.demapp.model.User;
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

	@GetMapping(value="/user/changePassword")
	public ModelAndView changePassword()
	{
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("authorized/changePassword");
		return modelAndView;
	}
	
	@PostMapping(value ="/user/changeUserPassword")
	public ModelAndView changeUserPassword(@RequestParam("newPassword") String newPassword, @RequestParam("confirmPassword") String confirmPassword)
	{
		ModelAndView modelAndView = new ModelAndView();
		User user = userService.findUserByEmail(userService.getCurrentUser().getEmail());
		if(!newPassword.equals(confirmPassword) && user != null)
		{
			modelAndView.addObject("userName", userService.getCurrentUser().getName() + " " + userService.getCurrentUser().getLastName());
			LOGGER.info("Reading username from the user object by passing username {} : " + userService.getCurrentUser().getEmail());
			modelAndView.addObject("errorMessage", "Password does not match");
			modelAndView.setViewName("authorized/changePassword");
		}
		else 
		{
			user.setPassword(newPassword);
			userService.saveUser(user);
			modelAndView.addObject("userName", userService.getCurrentUser().getName() + " " + userService.getCurrentUser().getLastName());
			LOGGER.info("Reading username from the user object by passing username {} : " + userService.getCurrentUser().getEmail());
			modelAndView.addObject("successMessage", "Password has been changed successfully");
			modelAndView.addObject("user", user);
			modelAndView.setViewName("authorized/changePassword");
		}
		return modelAndView;
	}
}
