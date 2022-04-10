package com.gl.employee.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.employee.entity.Employee;

public interface EmployeeManagementRepository extends JpaRepository<Employee, Integer> {

	List<Employee> findByfirstNameContainsAllIgnoreCase(String firstName);

	List<Employee> findAllByOrderByFirstNameAsc();
	
	List<Employee> findAllByOrderByFirstNameDesc();
}
