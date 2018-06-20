package com.chhaileng.ams.repository.entitymanager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.chhaileng.ams.entity.Category;

//@Repository
//@Transactional
public class CategoryRepository {

	@PersistenceContext
	private EntityManager em;
	
	public void save(Category category) {
		em.persist(category);
	}
	
	public Category findOne(Integer id) {
		return em.find(Category.class, id);
	}
	
	public List<Category> findAll() {
		return em.createQuery("select c from Category c", Category.class).getResultList();
	}
	
}
