package com.gl.employee.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.employee.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
