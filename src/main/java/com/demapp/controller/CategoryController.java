/**
 * 
 */
package com.demapp.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.demapp.model.Category;
import com.demapp.service.CategoryService;
import com.demapp.service.UserService;

/**
 * @author muthu_m
 *
 */
@Controller
public class CategoryController 
{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	UserService userService;
	
	@GetMapping(value="/admin/category")
	public ModelAndView listCategory()
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("userName", userService.getCurrentUser().getName() + " " + userService.getCurrentUser().getLastName());
		LOGGER.info("Reading username from the user object by passing username {} : " + userService.getCurrentUser().getEmail());
		modelAndView.addObject("categories", categoryService.findAll());
		modelAndView.setViewName("authorized/admin/category");
		return modelAndView;
	}
	
	@GetMapping(value="/admin/addCategory")
	public ModelAndView addCategory()
	{
		ModelAndView modelAndView = new ModelAndView();
		Category category = new Category();
		modelAndView.addObject("category", category);
		modelAndView.setViewName("authorized/admin/addCategory");
		return modelAndView;
	}
	
	@PostMapping(value = "/admin/addEditCategory")
	public ModelAndView createNewCategory(@Valid Category category, BindingResult bindingResult) 
	{
		ModelAndView modelAndView = new ModelAndView();
		Category categoryExists = (Category) categoryService.getCategoryByName(category.getName());
		if (categoryExists != null) 
		{
			bindingResult
					.rejectValue("name", "error.category",
							"There is already a category registered with the name provided");
		}
		if (bindingResult.hasErrors())
		{
			modelAndView.setViewName("authorized/admin/addCategory");
		}
		else 
		{
			categoryService.addOrUpdateCategory(category);
			modelAndView.addObject("successMessage", "Category has been added successfully");
			modelAndView.addObject("category", new Category());
			modelAndView.setViewName("redirect:/admin/category");
		}
		return modelAndView;
	}
	
	@DeleteMapping(value = "/admin/deleteCategory")
	public ModelAndView deleteCategory(@RequestParam("categoryId") long id) 
	{
		Category category = categoryService.getCategoryByID(id);
		String redirect = "redirect:/admin/category";
		if(category == null)
		{
			LOGGER.error("There is no category belongs to this id");
			return new ModelAndView(redirect);
		}
		categoryService.deleteCategoryById(id);
		return new ModelAndView(redirect);
	}

}
