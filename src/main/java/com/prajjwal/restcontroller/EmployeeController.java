package com.prajjwal.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prajjwal.employeeservice.EmployeeService;
import com.prajjwal.entity.Employee;

@RestController
@RequestMapping(value = "/api")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {

		this.employeeService = employeeService;
	}

	@RequestMapping(value = "/employee/create", method = RequestMethod.POST)
	public ResponseEntity<Employee> addNewEmployee(@RequestBody Employee employee) throws Exception {

		employeeService.saveEmployee(employee);
		return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
	}

	@GetMapping("/hello")
	public String hello() {
		return "logged in";
	}

	@RequestMapping(value = "/employee/getAll", method = RequestMethod.GET)
	public List<Employee> getAllEmployee() {
		return employeeService.getAllEmployee();
	}

	@RequestMapping(value = "/employee/findByFirstName/{firstName}", method = RequestMethod.GET)
	public List<Employee> getEmployeeByFirstName(@PathVariable String firstName) {
		return employeeService.getEmployeeByFirstName(firstName);
	}

	@RequestMapping(value = "/employee/updateByLastName/{oldLastName}/{newLastName}", method = RequestMethod.GET)
	public List<Employee> updateByLastName(@PathVariable String oldLastName, @PathVariable String newLastName) {
		return employeeService.updateByLastName(oldLastName, newLastName);
	}

	@RequestMapping(value = "/employee/deleteByLastName/{lastName}", method = RequestMethod.GET)
	public List<Employee> deleteByLastName(@PathVariable String lastName) {
		return employeeService.deleteByLastName(lastName);
	}

	@RequestMapping(value = "/employee/findByIdInElastic/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findByIdInElastic(@PathVariable String id) {
		Optional<Employee> employeeFromOpt = employeeService.findByIdInElastic(id);
		if (employeeFromOpt.isPresent()) {
			Employee employee = employeeFromOpt.get();
			return new ResponseEntity<>(employee, HttpStatus.ACCEPTED);
		} else
			return new ResponseEntity<>("No Employee present", HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/employee/search")
	public ResponseEntity<List<Employee>> searchEmployee(@RequestParam(required = true, name="q") String q){
		
		
		return ResponseEntity.ok(employeeService.searchEmployee("*"+q+"*"));
		
	}
}
