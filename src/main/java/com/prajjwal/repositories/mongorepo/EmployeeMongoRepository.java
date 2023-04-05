package com.prajjwal.repositories.mongorepo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.prajjwal.entity.Employee;

@Repository
public interface EmployeeMongoRepository extends MongoRepository<Employee, String> {

	public List<Employee> findByFirstName(String firstName);
	public List<Employee> findByLastName(String lastName);
	
	
}
