package com.chhaileng.ams.service.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chhaileng.ams.entity.Category;
import com.chhaileng.ams.repository.entitymanager.CategoryRepository;

@Service
public class CategoryServiceImp implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public void save(Category category) {
		categoryRepository.save(category);
	}

	@Override
	public Category findOne(Integer id) {
		return categoryRepository.findOne(id);
	}

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

}
