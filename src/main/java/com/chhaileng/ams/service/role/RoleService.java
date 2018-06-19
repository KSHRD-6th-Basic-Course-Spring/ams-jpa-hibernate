package com.chhaileng.ams.service.role;

import java.util.List;

import com.chhaileng.ams.entity.Role;

public interface RoleService {
	void save(Role role);
	Role findOne(Integer id);
	List<Role> findAll();
}
