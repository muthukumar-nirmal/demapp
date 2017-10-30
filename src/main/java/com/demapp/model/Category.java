/**
 * 
 */
package com.demapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author muthu_m
 *
 */
@Entity
@Table(name = "tbl_category")
public class Category {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="category_id")
	private Long categoryId;
	@Column(name="category_name")
	private String name;
	@Column(name="category_desc")
	private String description;
	
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
