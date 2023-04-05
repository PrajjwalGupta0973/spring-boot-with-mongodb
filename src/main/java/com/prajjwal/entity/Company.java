package com.prajjwal.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "company")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "company")
public class Company {

	@Id
	@Field(type = FieldType.Keyword)
	private String Id;
	@Field(type = FieldType.Text)
	private String name;

	@JsonIgnore
	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String company) {
		this.name = company;
	}

	@Override
	public String toString() {
		return "Company [Id=" + Id + ", name=" + name + "]";
	}
}
