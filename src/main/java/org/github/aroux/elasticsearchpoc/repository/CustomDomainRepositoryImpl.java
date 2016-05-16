package org.github.aroux.elasticsearchpoc.repository;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.highlight.HighlightBuilder;
import org.elasticsearch.search.highlight.HighlightField;
import org.github.aroux.elasticsearchpoc.entity.Domain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

@Repository
public class CustomDomainRepositoryImpl implements CustomDomainRepository {

    private final ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    public CustomDomainRepositoryImpl(ElasticsearchTemplate elasticsearchTemplate) {
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    @Override
    public Page<Domain> findByNameOrDescription(String name, String description, Pageable pageable) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQuery()
                        .should(matchQuery(Domain.FIELD_NAME, name))
                        .should(matchQuery(Domain.FIELD_DESCRIPTION, description)))
                .withHighlightFields(
                        new HighlightBuilder.Field(Domain.FIELD_NAME),
                        new HighlightBuilder.Field(Domain.FIELD_DESCRIPTION))
                .withPageable(pageable)
                .build();

        Page<Domain> page = elasticsearchTemplate.queryForPage(searchQuery, Domain.class, new SearchResultMapper() {
            @Override
            public <T> Page<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
                List<Domain> chunk = new ArrayList<>();
                for (SearchHit searchHit : response.getHits()) {
                    if (response.getHits().getHits().length <= 0) {
                        break;
                    }

                    Map<String, HighlightField> highlightFieldMap = searchHit.getHighlightFields();
                    Domain domain = new Domain();
                    domain.setId(searchHit.getId());

                    HighlightField nameHField = highlightFieldMap.get(Domain.FIELD_NAME);
                    String name;
                    if (nameHField != null) {
                        name = nameHField.toString();
                    } else {
                        name = (String) searchHit.getSource().get(Domain.FIELD_NAME);
                    }
                    domain.setName(name);
                    String description = highlightFieldMap.get(Domain.FIELD_DESCRIPTION).toString();
                    if (name == null) {
                        description = (String) searchHit.getSource().get(Domain.FIELD_DESCRIPTION);
                    }
                    domain.setDescription(description);
                    chunk.add(domain);
                }
                if (chunk.size() > 0) {
                    return new PageImpl<T>((List<T>) chunk);
                }
                return new PageImpl<T>((List<T>) chunk);
            }
        });
        return page;
    }
}
