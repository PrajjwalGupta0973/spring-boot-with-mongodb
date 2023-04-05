package com.prajjwal.employeeservice;

import java.util.List;
import java.util.Optional;

import com.prajjwal.entity.Employee;

public interface EmployeeService {

	// MongoDb
	Employee saveEmployee(Employee employee) throws Exception;

	List<Employee> getAllEmployee();

	List<Employee> getEmployeeByFirstName(String firstName);

	List<Employee> updateByLastName(String oldLastName, String newLastName);

	List<Employee> deleteByLastName(String lastName);

	void validateCompany(Employee employee) throws Exception;

	// ElasticSearch
	Employee saveEmployeeInElastic(Employee employee);

	Optional<Employee> findByIdInElastic(String id);

	// mailservice
	String emailEmployeeData(String firstname);

	List<Employee> searchEmployee(String queryString);

}
