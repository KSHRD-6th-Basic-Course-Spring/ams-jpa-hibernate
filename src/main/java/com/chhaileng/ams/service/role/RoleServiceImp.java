package com.chhaileng.ams.service.role;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chhaileng.ams.entity.Role;
import com.chhaileng.ams.repository.jparepository.RoleRepository;

@Service
public class RoleServiceImp implements RoleService {

	private RoleRepository roleRepository;

	public RoleServiceImp(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
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
