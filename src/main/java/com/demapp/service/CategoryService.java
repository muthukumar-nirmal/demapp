/**
 * 
 */
package com.demapp.service;

import java.util.List;

import com.demapp.model.Category;


/**
 * @author muthu_m
 *
 */
public interface CategoryService 
{
	List<Category> findAll();
	Category addOrUpdateCategory(Category category);
	void deleteCategoryById(Long id);
	Category getCategoryByID(Long id);
	Category getCategoryByName(String name); 
}
