package org.github.aroux.elasticsearchpoc.service.impl;

import org.github.aroux.elasticsearchpoc.dto.SearchAllResult;
import org.github.aroux.elasticsearchpoc.entity.Domain;
import org.github.aroux.elasticsearchpoc.entity.LKP;
import org.github.aroux.elasticsearchpoc.entity.View;
import org.github.aroux.elasticsearchpoc.repository.DomainRepository;
import org.github.aroux.elasticsearchpoc.repository.LKPRepository;
import org.github.aroux.elasticsearchpoc.repository.ViewRepository;
import org.github.aroux.elasticsearchpoc.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    private final DomainRepository domainRepository;
    private final ViewRepository viewRepository;
    private final LKPRepository lkpRepository;

    private final static int PAGE_SIZE = 5;

    @Autowired
    public SearchServiceImpl(DomainRepository domainRepository, ViewRepository viewRepository, LKPRepository lkpRepository) {
        this.domainRepository = domainRepository;
        this.viewRepository = viewRepository;
        this.lkpRepository = lkpRepository;
    }

    @Override
    public List<Domain> searchForDomain(String pattern, int pageNumber) {
        Page<Domain> page = domainRepository.findByNameOrDescription(pattern, pattern, new PageRequest(pageNumber, PAGE_SIZE));
        return page.getContent();
    }

    @Override
    public List<View> searchForView(String pattern, int pageNumber) {
        Page<View> page = viewRepository.findByNameOrDescription(pattern, pattern, new PageRequest(pageNumber, PAGE_SIZE));
        return page.getContent();
    }

    @Override
    public List<LKP> searchForLKP(String pattern, int pageNumber) {
        Page<LKP> page = lkpRepository.findByNameOrDescriptionOrValuesKeyOrValuesValue(pattern, pattern, pattern, pattern, new PageRequest(pageNumber, PAGE_SIZE));
        return page.getContent();
    }

    @Override
    public SearchAllResult searchForAll(String pattern, int pageNumber) {
        SearchAllResult searchAllResult = new SearchAllResult();

        searchAllResult.setDomains(searchForDomain(pattern, pageNumber));
        searchAllResult.setViews(searchForView(pattern, pageNumber));
        searchAllResult.setLkps(searchForLKP(pattern, pageNumber));

        return searchAllResult;
    }
}
