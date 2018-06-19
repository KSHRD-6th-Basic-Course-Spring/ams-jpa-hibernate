package com.chhaileng.ams.service.article;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chhaileng.ams.entity.Article;
import com.chhaileng.ams.repository.entitymanager.ArticleRepository;

@Service
public class ArticleServiceImp implements ArticleService {
	
	@Autowired
	private ArticleRepository articleRepository;

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
		articleRepository.update(article);
	}
	
}
