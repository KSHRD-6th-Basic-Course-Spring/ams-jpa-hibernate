package com.chhaileng.ams.repository.entitymanager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.chhaileng.ams.entity.Role;

@Repository
@Transactional
public class RoleRepository {

	@PersistenceContext
	private EntityManager em;
	
	public void save(Role role) {
		em.persist(role);
	}
	
	public Role findOne(Integer id) {
		return em.find(Role.class, id);
	}
	
	public List<Role> findAll() {
		return em.createQuery("select r from Role r", Role.class).getResultList();
	}
	
}
