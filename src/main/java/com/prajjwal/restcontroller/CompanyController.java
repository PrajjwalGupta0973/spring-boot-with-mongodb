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

import com.prajjwal.companyservice.CompanyService;
import com.prajjwal.entity.Company;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api")
@Slf4j
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	public CompanyController(CompanyService companyService) {

		this.companyService = companyService;
	}

	@RequestMapping(value = "/company/create", method = RequestMethod.POST)
	public Company addNewCompany(@RequestBody Company Company) {

		return companyService.saveCompany(Company);
	}

	@RequestMapping(value = "/company/getAll", method = RequestMethod.GET)
	public List<Company> getAllCompany() {

		log.debug("Request: getAll.");
		return companyService.getAllCompany();
	}

	@RequestMapping(value = "/company/findByName/{Name}", method = RequestMethod.GET)
	public List<Company> getCompanyByName(@PathVariable String Name) {
		log.debug("Rquest: findByName {}", Name);

		return companyService.getCompanyByName(Name);
	}

	@RequestMapping(value = "/company/updateByName/{oldName}/{newName}", method = RequestMethod.GET)
	public List<Company> updateByName(@PathVariable String oldName, @PathVariable String newName) {
		return companyService.updateByName(oldName, newName);
	}

	@RequestMapping(value = "/company/deleteByName/{Name}", method = RequestMethod.DELETE)
	public List<Company> deleteByName(@PathVariable String Name) {
		return companyService.deleteByName(Name);
	}

	@RequestMapping(value = "/company/findByIdInElastic/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findByIdInElastic(@PathVariable String id,
			@RequestParam(value = "username") String username) {
		Optional<Company> companyFromOpt = companyService.findByIdInElastic(id);
		if (companyFromOpt.isPresent()) {
			Company company = companyFromOpt.get();
			return new ResponseEntity<>(company, HttpStatus.ACCEPTED);
		} else
			return new ResponseEntity<>("No Company found", HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/company/search")
	public ResponseEntity<List<Company>> searchCompany(@RequestParam("q") String query) {
		log.info("Rest request to searchCompany(\"{}\")", query);
		return ResponseEntity.ok(companyService.searchCompany(query));
	}
}
