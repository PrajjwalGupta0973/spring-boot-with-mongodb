package com.prajjwal.repositories.elasticrepo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.prajjwal.entity.Company;

@Repository
public interface CompanyElasticRepository extends ElasticsearchRepository<Company, String>{

}
