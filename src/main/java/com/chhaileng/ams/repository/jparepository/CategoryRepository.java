package com.chhaileng.ams.repository.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chhaileng.ams.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
