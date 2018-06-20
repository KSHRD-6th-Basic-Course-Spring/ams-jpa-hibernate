package com.chhaileng.ams.service.category;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chhaileng.ams.entity.Category;
import com.chhaileng.ams.repository.jparepository.CategoryRepository;

@Service
public class CategoryServiceImp implements CategoryService {

	private CategoryRepository categoryRepository;

	public CategoryServiceImp(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
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
