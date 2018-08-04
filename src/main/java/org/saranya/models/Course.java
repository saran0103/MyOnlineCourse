package org.saranya.models;

public class Course {

    private int id;
    private static int nextId = 1;

    private String name;
    private Language language;
    private Title title;
    private Material material;
    private CourseType coursetype;

    public Course() {
        id = nextId;
        nextId++;
    }

    public Course(String aName, Language aLanguage, Title aTitle,
                  Material aMaterial, CourseType aCourseType) {

        this();

        name = aName;
        language = aLanguage;
        title = aTitle;
        material = aMaterial;
        coursetype = aCourseType;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public CourseType getCourseType() {
        return coursetype;
    }

    public void setCourseType(CourseType coursetype) {
        this.coursetype = coursetype;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        return id == course.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
