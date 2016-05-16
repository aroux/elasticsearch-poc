package org.github.aroux.elasticsearchpoc.repository;

import org.github.aroux.elasticsearchpoc.entity.Domain;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface DomainRepository extends ElasticsearchRepository<Domain, String>, CustomDomainRepository {

}
