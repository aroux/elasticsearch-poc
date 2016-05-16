package org.github.aroux.elasticsearchpoc.repository;

import org.github.aroux.elasticsearchpoc.entity.View;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ViewRepository extends ElasticsearchRepository<View, String> {

    Page<View> findByNameOrDescription(String name, String description, Pageable pageable);
}
