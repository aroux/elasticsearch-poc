package org.github.aroux.elasticsearchpoc.service;

import org.github.aroux.elasticsearchpoc.dto.SearchAllResult;
import org.github.aroux.elasticsearchpoc.entity.Domain;
import org.github.aroux.elasticsearchpoc.entity.LKP;
import org.github.aroux.elasticsearchpoc.entity.View;

import java.util.List;

public interface SearchService {

    List<Domain> searchForDomain(String pattern, int pageNumber);

    List<View> searchForView(String pattern, int pageNumber);

    List<LKP> searchForLKP(String pattern, int pageNumber);

    SearchAllResult searchForAll(String pattern, int pageNumber);

}
