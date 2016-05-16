package org.github.aroux.elasticsearchpoc.repository;

import org.github.aroux.elasticsearchpoc.entity.Domain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomDomainRepository {

    Page<Domain> findByNameOrDescription(String name, String description, Pageable pageable);

}
