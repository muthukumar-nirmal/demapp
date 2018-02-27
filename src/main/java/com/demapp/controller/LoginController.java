package com.demapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.demapp.model.User;
import com.demapp.service.UserService;

@Controller
public class LoginController 
{
	
	@Autowired
	private UserService userService;

	@GetMapping(value={"/", "/login"})
	public ModelAndView login()
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("unauthorized/login");
		return modelAndView;
	}
	
	@GetMapping(value="/home")
	public ModelAndView home()
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("unauthorized/home");
		return modelAndView;
	}
	
	@GetMapping(value="/aboutUs")
	public ModelAndView aboutUs()
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("unauthorized/aboutUs");
		return modelAndView;
	}
	
	@GetMapping(value="/contactUs")
	public ModelAndView contactUs()
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("unauthorized/contactUs");
		return modelAndView;
	}
	
	@GetMapping(value="/registration")
	public ModelAndView registration()
	{
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("unauthorized/registration");
		return modelAndView;
	}
	
	@PostMapping(value = "/registration")
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) 
	{
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) 
		{
			bindingResult
					.rejectValue("email", "error.user",
							"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors())
		{
			modelAndView.setViewName("unauthorized/registration");
		}
		else 
		{
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("unauthorized/registration");
			
		}
		return modelAndView;
	}
}
