package com.prajjwal.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prajjwal.employeeservice.EmployeeService;

@RestController
@RequestMapping(value = "/api")
public class MailController {

	@Autowired
	EmployeeService employeeService;
	
	
	
	@GetMapping("/emailemployeedata/{firstname}")
	String emailEmployeeData(@PathVariable String firstname) {
		return employeeService.emailEmployeeData(firstname);
	}
}
