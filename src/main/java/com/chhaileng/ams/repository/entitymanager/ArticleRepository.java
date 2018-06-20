package com.chhaileng.ams.repository.entitymanager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.chhaileng.ams.entity.Article;

//@Repository
public class ArticleRepository {

	@PersistenceContext
	private EntityManager em;
	
	public void save(Article article) {
		em.persist(article);
	}
	
	public Article findOne(Integer id) {
		return em.find(Article.class, id);
	}
	
	public List<Article> findAll() {
		return em.createQuery("select a from Article a order by a.id desc", Article.class).getResultList();
	}
	
	public void delete(Article article) {
		em.remove(article);
	}
	
	public void update(Article article) {
		em.merge(article);
	}
	
}
