package com.prajjwal.companyservice;

import java.util.List;
import java.util.Optional;

import com.prajjwal.entity.Company;

public interface CompanyService {

	// MongoDb
	Company saveCompany(Company company);

	List<Company> getAllCompany();

	List<Company> getCompanyByName(String firstName);

	List<Company> updateByName(String oldLastName, String newLastName);

	List<Company> deleteByName(String lastName);

	// ElasticSearch
	Company saveCompanyInElastic(Company company);

	Optional<Company> findByIdInElastic(String id);

	List<Company> searchCompany(String query);
}
