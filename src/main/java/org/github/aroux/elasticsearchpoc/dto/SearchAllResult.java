package org.github.aroux.elasticsearchpoc.dto;

import org.github.aroux.elasticsearchpoc.entity.Domain;
import org.github.aroux.elasticsearchpoc.entity.LKP;
import org.github.aroux.elasticsearchpoc.entity.View;

import java.util.List;

public class SearchAllResult {

    private List<Domain> domains;

    private List<View> views;

    private List<LKP> lkps;

    public List<Domain> getDomains() {
        return domains;
    }

    public void setDomains(List<Domain> domains) {
        this.domains = domains;
    }

    public List<View> getViews() {
        return views;
    }

    public void setViews(List<View> views) {
        this.views = views;
    }

    public List<LKP> getLkps() {
        return lkps;
    }

    public void setLkps(List<LKP> lkps) {
        this.lkps = lkps;
    }

    @Override
    public String toString() {
        return "SearchAllResult{" +
                "domains=" + domains +
                ", views=" + views +
                ", lkps=" + lkps +
                '}';
    }
}
