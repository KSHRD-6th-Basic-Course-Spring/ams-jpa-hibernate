package com.chhaileng.ams.repository.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chhaileng.ams.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

}
