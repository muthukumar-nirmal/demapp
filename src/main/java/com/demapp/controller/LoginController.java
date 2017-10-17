package com.demapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.demapp.model.User;
import com.demapp.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("unauthorized/login");
		return modelAndView;
	}
	
	@RequestMapping(value={"/home"}, method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("unauthorized/home");
		return modelAndView;
	}
	
	@RequestMapping(value={"/aboutUs"}, method = RequestMethod.GET)
	public ModelAndView aboutUs(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("unauthorized/aboutUs");
		return modelAndView;
	}
	
	@RequestMapping(value={"/contactUs"}, method = RequestMethod.GET)
	public ModelAndView contactUs(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("unauthorized/contactUs");
		return modelAndView;
	}
	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("unauthorized/registration");
		return modelAndView;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("unauthorized/registration");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("unauthorized/registration");
			
		}
		return modelAndView;
	}
}
