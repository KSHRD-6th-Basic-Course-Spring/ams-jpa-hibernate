package com.chhaileng.ams.service.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chhaileng.ams.entity.Role;
import com.chhaileng.ams.repository.entitymanager.RoleRepository;

@Service
public class RoleServiceImp implements RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public void save(Role role) {
		roleRepository.save(role);
	}

	@Override
	public Role findOne(Integer id) {
		return roleRepository.findOne(id);
	}

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}
	
}
