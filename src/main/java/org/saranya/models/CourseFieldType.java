package org.saranya.models;


public enum CourseFieldType {

    LANGUAGE ("Language"),
    TITLE ("Title"),
    MATERIAL ("Material"),
    COURSE_FIELD_TYPE ("Course Type"),
    ALL ("All");

    private final String name;

    CourseFieldType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
