package com.prajjwal.repositories.elasticrepo;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.prajjwal.entity.Employee;

@Repository
public interface EmployeeElasticRepository extends ElasticsearchRepository<Employee, String>{

}
