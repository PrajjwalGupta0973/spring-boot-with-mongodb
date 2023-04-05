package com.prajjwal.companyservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.prajjwal.entity.Company;
import com.prajjwal.repositories.elasticrepo.CompanyElasticRepository;
import com.prajjwal.repositories.mongorepo.CompanyMongoRepository;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyElasticRepository companyElasticRepository;

	@Value("${profile}")
	private static String profile;

	@Autowired
	private CompanyMongoRepository companyRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public Company saveCompany(Company company) {
		System.out.println("Saving Company under profile : " + profile);
//		saveCompanyInElastic(company);
		return companyRepository.save(company);

	}

	// MongoDb
	@Override
	public List<Company> getAllCompany() {
		return companyRepository.findAll();
	}

	@Override
	public List<Company> getCompanyByName(String name) {
		return companyRepository.findByName(name);
	}

	@Override
	public List<Company> updateByName(String oldName, String newName) {
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(oldName));
		Update update = new Update();
		update.set("name", newName);
		mongoTemplate.updateFirst(query, update, Company.class);
		return companyRepository.findByName(newName);
	}

	@Override
	public List<Company> deleteByName(String name) {
		List<Company> Companies = companyRepository.findByName(name);
		List<Company> tempCompanies = Companies;
		for (Company company : Companies) {
			System.out.println("de: " + company.getName() + ",id :" + company.getId());
			companyRepository.deleteById(company.getId());
		}
		return tempCompanies;
	}

	@Override
	public Company saveCompanyInElastic(Company company) {
		return companyElasticRepository.save(company);
	}

	@Override
	public Optional<Company> findByIdInElastic(String id) {
		return companyElasticRepository.findById(id);

	}

	@Override
	public List<Company> searchCompany(String query) {
		List<Company> list = new ArrayList<Company>();
		companyElasticRepository.search(QueryBuilders.queryStringQuery(query)).forEach(list::add);
		return list;
	}
}
