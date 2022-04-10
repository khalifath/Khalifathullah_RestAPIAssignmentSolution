package com.gl.employee.service;

import java.util.List;

import com.gl.employee.entity.Employee;
import com.gl.employee.entity.Role;
import com.gl.employee.entity.User;

public interface EmployeeManagementService {

	public List<Employee> findAll();

	public Employee findById(int theId);

	public void save(Employee theEmployee);

	public void deleteById(int theId);

	public List<Employee> searchByFirstName(String firstName);

	public List<Employee> sortByFirstNameAsc();

	public List<Employee> sortByFirstNameDesc();

	public User saveUser(User user);

	public Role saveRole(Role role);
}
