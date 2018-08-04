package org.saranya.models.data;

import javafx.geometry.Pos;
import org.saranya.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import org.saranya.models.CourseField;
public class CourseData {

    private ArrayList<Course> courses = new ArrayList<>();

    private static CourseData instance;

    private CourseFieldData<Language> languages = new CourseFieldData<>();

    private CourseFieldData<Title> titles = new CourseFieldData<>();

    private CourseFieldData<Material> materials = new CourseFieldData<>();

    private CourseFieldData<CourseType> courseTypes = new CourseFieldData<>();


    private CourseData(){CourseDataImporter.loadData(this); }

    public static CourseData getInstance() {
        if(instance == null) {
            instance = new CourseData();
        }
        return instance;
    }
    public Course findById(int id) {
        for (Course course : courses) {
            if(course.getId() == id)
                return course;
        }
        return null;
    }
    public ArrayList<Course> findAll() {
        return courses;
    }

    public ArrayList<Course> findByColumnAndValue(CourseFieldType column, String value){

        ArrayList<Course> matchingCourses = new ArrayList<>();

        for (Course course : courses){
            if(getFieldByType(course, column).contains(value))
                matchingCourses.add(course);
        }
        return matchingCourses;
    }
    public ArrayList<Course> findByValue(String value){

        ArrayList<Course> matchingCourses = new ArrayList<>();

        for (Course course : courses){
            if(course.getName().toLowerCase().contains(value)){
                matchingCourses.add(course);
                continue;
            }
            for (CourseFieldType column:CourseFieldType.values()){
                if (column != CourseFieldType.ALL && getFieldByType(course, column).contains(value)){
                    matchingCourses.add(course);
                    break;
                }
            }
        }
        return matchingCourses;
    }
    public void add(Course course){
        courses.add(course);
    }
    private static CourseField getFieldByType(Course course, CourseFieldType type){
        switch(type){
            case LANGUAGE:
                return course.getLanguage();
            case TITLE:
                return course.getTitle();
            case MATERIAL:
                return course.getMaterial();
            case COURSE_FIELD_TYPE:
                return course.getCourseType();
        }
        throw new IllegalArgumentException("Cannot get field of type " + type);
    }
    public CourseFieldData<Language> getLanguages(){
        return languages;
    }

    public CourseFieldData<Title> getTitles() {
        return titles;
    }

    public CourseFieldData<Material> getMaterials() {
        return materials;
    }

    public CourseFieldData<CourseType> getCourseTypes() {
        return courseTypes;
    }
}
