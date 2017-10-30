/**
 * 
 */
package com.demapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demapp.model.Category;


/**
 * @author muthu_m
 *
 */

@Repository("categoryRepository")
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	List<Category> findByName(String name);
}
