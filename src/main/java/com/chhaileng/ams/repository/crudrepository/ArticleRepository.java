package com.chhaileng.ams.repository.crudrepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chhaileng.ams.entity.Article;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Integer>{

}
