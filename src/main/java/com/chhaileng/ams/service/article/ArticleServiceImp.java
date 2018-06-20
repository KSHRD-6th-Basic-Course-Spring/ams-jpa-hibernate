package com.chhaileng.ams.service.article;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chhaileng.ams.entity.Article;
import com.chhaileng.ams.repository.jparepository.ArticleRepository;

@Service
public class ArticleServiceImp implements ArticleService {
	
	private ArticleRepository articleRepository;

	public ArticleServiceImp(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}
	
	@Override
	public void save(Article article) {
		articleRepository.save(article);
	}

	@Override
	public Article findOne(Integer id) {
		return articleRepository.findOne(id);
	}

	@Override
	public List<Article> findAll() {
		return articleRepository.findAll();
	}
	
	@Override
	public void delete(Integer id) {
		articleRepository.delete(articleRepository.findOne(id));
	}
	
	@Override
	public void update(Article article) {
		// Entity Manager
		// articleRepository.update(article);
		
		// JPA Repository
		articleRepository.saveAndFlush(article);
	}
	
}
