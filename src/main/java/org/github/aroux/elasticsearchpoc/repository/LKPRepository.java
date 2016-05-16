package org.github.aroux.elasticsearchpoc.repository;

import org.github.aroux.elasticsearchpoc.entity.LKP;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface LKPRepository extends ElasticsearchRepository<LKP, String> {

    Page<LKP> findByNameOrDescriptionOrValuesKeyOrValuesValue(String name, String description,
                                                              String valuesKey, String valuesValue,
                                                              Pageable pageable);

}
