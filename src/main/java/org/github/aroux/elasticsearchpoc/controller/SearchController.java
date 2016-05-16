package org.github.aroux.elasticsearchpoc.controller;

import org.github.aroux.elasticsearchpoc.dto.SearchAllResult;
import org.github.aroux.elasticsearchpoc.dto.SearchRequest;
import org.github.aroux.elasticsearchpoc.entity.Domain;
import org.github.aroux.elasticsearchpoc.entity.LKP;
import org.github.aroux.elasticsearchpoc.entity.View;
import org.github.aroux.elasticsearchpoc.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping(path = "/domains", method = RequestMethod.POST)
    @ResponseBody
    public List<Domain> searchForDomain(@RequestBody SearchRequest request) {
        return searchService.searchForDomain(request.getPattern(), request.getPageNumber());
    }

    @RequestMapping(path = "/views", method = RequestMethod.POST)
    @ResponseBody
    public List<View> searchForView(@RequestBody SearchRequest request) {
        return searchService.searchForView(request.getPattern(), request.getPageNumber());
    }

    @RequestMapping(path = "/lkps", method = RequestMethod.POST)
    @ResponseBody
    public List<LKP> searchForLKP(@RequestBody SearchRequest request) {
        return searchService.searchForLKP(request.getPattern(), request.getPageNumber());
    }

    @RequestMapping(path = "/all", method = RequestMethod.POST)
    @ResponseBody
    public SearchAllResult searchForAll(@RequestBody SearchRequest request) {
        return searchService.searchForAll(request.getPattern(), request.getPageNumber());
    }

}
