/**
 * 
 */
package com.demapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demapp.model.Category;


/**
 * @author muthu_m
 *
 */

@Repository("categoryRepository")
public interface CategoryRepository extends JpaRepository<Category, Long> 
{
	Category findByName(String name);
}
