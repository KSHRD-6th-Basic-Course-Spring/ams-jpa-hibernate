package com.chhaileng.ams.service.article;

import java.util.List;

import com.chhaileng.ams.entity.Article;

public interface ArticleService {
	void save(Article article);
	Article findOne(Integer id);
	List<Article> findAll();
	void delete(Integer id);
	void update(Article article);
}
