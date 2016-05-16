package org.github.aroux.elasticsearchpoc.dto;

public class SearchRequest {

    private String pattern;

    private int pageNumber;

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    @Override
    public String toString() {
        return "SearchRequest{" +
                "pattern='" + pattern + '\'' +
                ", pageNumber=" + pageNumber +
                '}';
    }
}
