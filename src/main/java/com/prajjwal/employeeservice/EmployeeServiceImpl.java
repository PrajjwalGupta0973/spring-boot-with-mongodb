package com.prajjwal.employeeservice;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.prajjwal.entity.Company;
import com.prajjwal.entity.Employee;
import com.prajjwal.repositories.elasticrepo.EmployeeElasticRepository;
import com.prajjwal.repositories.mongorepo.EmployeeMongoRepository;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Personalization;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Value("${fromemail}")
	private String fromEmail;

	@Value("${toemail}")
	private String toEmail;

	@Value("${firsttemplate}")
	private String templateId;

	@Autowired
	private EmployeeMongoRepository employeeMongoRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private EmployeeElasticRepository employeeElasticRepository;

	// @Autowired
	// EmployeeNeoRepository employeeNeoRepository;

	@Autowired
	SendGrid sendgrid;

	@Override
	public Employee saveEmployee(Employee employee) throws Exception {

		validateCompany(employee);

		// save in ElasticSearch
//		saveEmployeeInElastic(employee);

		return employeeMongoRepository.save(employee);

	}

	// MongoDb
	@Override
	public List<Employee> getAllEmployee() {
		return employeeMongoRepository.findAll();
	}

	@Override
	public List<Employee> getEmployeeByFirstName(String firstName) {
		return employeeMongoRepository.findByFirstName(firstName);
	}

	@Override
	public List<Employee> updateByLastName(String oldLastName, String newLastName) {
		Query query = new Query();
		query.addCriteria(Criteria.where("lastName").is(oldLastName));
		Update update = new Update();
		update.set("lastName", newLastName);
		mongoTemplate.updateFirst(query, update, Employee.class);
		return employeeMongoRepository.findByLastName(newLastName);
	}

	@Override
	public List<Employee> deleteByLastName(String lastName) {
		List<Employee> employees = employeeMongoRepository.findByLastName(lastName);
		List<Employee> tempEmployees = employees;
		for (Employee employee : employees) {
			employeeMongoRepository.deleteById(employee.getId());
		}
		return tempEmployees;
	}

	@Override
	public void validateCompany(Employee employee) throws Exception {
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(employee.getCompany()));
		// System.out.println(mongoTemplate.find(query, Company.class));
		List<Company> companies = mongoTemplate.find(query, Company.class);
		// System.out.println(companies.get(0).getName());
		Company company = companies.get(0);

		if (company == null) {
			throw new Exception("Company doesn't exist!");
		}
	}

	// ElasticSearch
	@Override
	public Employee saveEmployeeInElastic(Employee employee) {

		return employeeElasticRepository.save(employee);
	}

	@Override
	public Optional<Employee> findByIdInElastic(String id) {
		return employeeElasticRepository.findById(id);
	}


	// mail
	@Override
	public String emailEmployeeData(String firstname) {

		List<Employee> employees = getEmployeeByFirstName(firstname);

		Email fromEmail = new Email();
		fromEmail.setEmail(this.fromEmail);

		Email toEmail = new Email();
		toEmail.setEmail(this.toEmail);

		Mail mail = new Mail(fromEmail, "EmployeeData", toEmail,
				new Content("text/plain", employees.get(0).toString()));

		mail.setTemplateId(templateId);
		Personalization personalization = new Personalization();
		personalization.addCc(fromEmail);

		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			this.sendgrid.api(request);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return "Mail has been sent. Check it in your inbox.";
	}

	@Override
	public List<Employee> searchEmployee(String queryString) {
		List<Employee> searchResults = new ArrayList<>();
		Iterator<Employee> searchResultSIterator = employeeElasticRepository.search(queryStringQuery(queryString))
				.iterator();

		while (searchResultSIterator.hasNext()) {
			searchResults.add(searchResultSIterator.next());
		}
		return searchResults;
	}

}
