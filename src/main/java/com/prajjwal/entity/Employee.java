package com.prajjwal.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
//import org.springframework.data.mongodb.core.mapping.Document;

@Document(indexName = "employee")
@org.springframework.data.mongodb.core.mapping.Document(collection = "employee")
public class Employee {

	@Id
	@Field(type = FieldType.Keyword)
	private String id;
	@Field(type = FieldType.Text)
	private String firstName;
	@Field(type = FieldType.Text)
	private String lastName;
	@Field(type = FieldType.Text)
	private String company;
	@Field(type = FieldType.Boolean)
	private boolean check;
	private float salary;

//@JsonIgnore
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public String haveId() {
		return id;
	}

	@Override
	public String toString() {
		return String.format("id=%s, firstName='%s', lastName='%s', company='%s', salary='%f'", id, firstName, lastName,
				company.toString(), salary);
	}
}
