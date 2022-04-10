package com.gl.employee.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.employee.entity.Employee;
import com.gl.employee.entity.Role;
import com.gl.employee.entity.User;
import com.gl.employee.service.EmployeeManagementService;

@RestController
@RequestMapping("/api")
public class EmployeeManagementRestController {

	private EmployeeManagementService employeeManagementService;

	@Autowired
	public EmployeeManagementRestController(EmployeeManagementService theEmployeeService) {
		employeeManagementService = theEmployeeService;
	}

	@PostMapping("/user")
	public User saveUser(@RequestBody User user) {
		return employeeManagementService.saveUser(user);
	}

	@PostMapping("/role")
	public Role saveRole(@RequestBody Role role) {
		return employeeManagementService.saveRole(role);
	}

	@GetMapping("/employees")
	public List<Employee> findAll() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> currentPrincipalName = authentication.getAuthorities();
		System.out.println(currentPrincipalName);
		return employeeManagementService.findAll();
	}

	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId) {

		Employee theEmployee = employeeManagementService.findById(employeeId);

		if (theEmployee == null) {
			throw new RuntimeException("Invalid Employee id-> " + employeeId);
		}

		return theEmployee;
	}

	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee) {

		theEmployee.setId(0);

		employeeManagementService.save(theEmployee);

		return theEmployee;
	}

	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee theEmployee) {

		employeeManagementService.save(theEmployee);

		return theEmployee;
	}

	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {

		Employee tempEmployee = employeeManagementService.findById(employeeId);

		if (tempEmployee == null) {
			throw new RuntimeException("Invalid Employee id-> " + employeeId);
		}

		employeeManagementService.deleteById(employeeId);

		return "Deleted employee id -> " + employeeId;
	}

	@GetMapping("/employees/search/{firstName}")
	public List<Employee> searchByFirstName(@PathVariable String firstName) {
		return employeeManagementService.searchByFirstName(firstName);
	}

	@GetMapping("/employees/sort")
	public List<Employee> sortByFirstName(String order) {

		if (order.equals("asc"))
			return employeeManagementService.sortByFirstNameAsc();
		else if (order.equals("desc"))
			return employeeManagementService.sortByFirstNameDesc();
		else
			return null;
	}

}
