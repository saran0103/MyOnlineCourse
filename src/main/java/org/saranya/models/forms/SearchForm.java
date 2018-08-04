package org.saranya.models.forms;

import org.saranya.models.CourseFieldType;

public class SearchForm {

    // The search options
    private CourseFieldType[] fields = CourseFieldType.values();

    // The selected search options
    private CourseFieldType searchField = CourseFieldType.ALL;

    // The search string
    private String keyword;

    public CourseFieldType getSearchField() {
        return searchField;
    }

    public void setSearchField(CourseFieldType searchField) {
        this.searchField = searchField;
    }

    public CourseFieldType[] getFields() {
        return fields;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
