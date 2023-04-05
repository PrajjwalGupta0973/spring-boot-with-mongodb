package com.prajjwal.repositories.mongorepo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.prajjwal.entity.Company;

@Repository
public interface CompanyMongoRepository extends MongoRepository<Company, String>{
	public List<Company> findByName(String name);
	
}
