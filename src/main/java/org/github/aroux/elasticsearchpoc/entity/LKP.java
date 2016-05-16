package org.github.aroux.elasticsearchpoc.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Document(indexName = "dhu", type = "lkp")
public class LKP {

    @Id
    private String id;

    @Field(index = FieldIndex.not_analyzed)
    private String domain;

    private String name;

    private String description;

    @Field(type = FieldType.Nested)
    private List<LKPValue> values;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<LKPValue> getValues() {
        return values;
    }

    public void setValues(List<LKPValue> values) {
        this.values = values;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Override
    public String toString() {
        return "LKP{" +
                "id='" + id + '\'' +
                ", domain='" + domain + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", values=" + values +
                '}';
    }

}
