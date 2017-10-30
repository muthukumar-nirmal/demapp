/**
 * 
 */
package com.demapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.demapp.service.CategoryService;
import com.demapp.service.UserService;

/**
 * @author muthu_m
 *
 */
@Controller
public class CategoryController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/admin/category", method = RequestMethod.GET)
	public ModelAndView listCategory()
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("userName", userService.getCurrentUser().getName() + " " + userService.getCurrentUser().getLastName());
		LOGGER.info("Reading username from the user object by passing username {} : " + userService.getCurrentUser().getEmail());
		modelAndView.addObject("categories", categoryService.findAll());
		modelAndView.setViewName("authorized/admin/category");
		return modelAndView;
	}

}
