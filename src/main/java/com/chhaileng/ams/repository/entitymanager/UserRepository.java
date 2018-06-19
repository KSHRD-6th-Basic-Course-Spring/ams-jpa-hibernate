package com.chhaileng.ams.repository.entitymanager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.chhaileng.ams.entity.User;

@Repository
@Transactional
public class UserRepository {

	@PersistenceContext
	private EntityManager em;
	
	public void save(User user) {
		em.persist(user);
	}
	
	public User findOne(Integer id) {
		return em.find(User.class, id);
	}
	
	public List<User> findAll() {
		return em.createQuery("select u from User u", User.class).getResultList();
	}
	
}
