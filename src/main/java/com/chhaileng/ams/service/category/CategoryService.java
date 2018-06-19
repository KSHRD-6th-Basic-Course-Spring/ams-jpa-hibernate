package com.chhaileng.ams.service.category;

import java.util.List;

import com.chhaileng.ams.entity.Category;

public interface CategoryService {
	void save(Category category);
	Category findOne(Integer id);
	List<Category> findAll();
}
