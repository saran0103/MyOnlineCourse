package org.saranya.controllers;

import org.saranya.models.Course;
import org.saranya.models.CourseField;
import org.saranya.models.CourseFieldType;
import org.saranya.models.data.CourseData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping(value = "list")
public class ListController {

    private CourseData courseData = CourseData.getInstance();

    @RequestMapping(value = "")
    public String list(Model model) {
        CourseFieldType[] fields = CourseFieldType.values();
        model.addAttribute("fields", fields);
        return "list";
    }

    @RequestMapping(value = "values")
    public String listColumnValues(Model model, @RequestParam CourseFieldType column) {

        if (column.equals(CourseFieldType.ALL)) {
            return "redirect:/list/all";
        }


        ArrayList<? extends CourseField> items;

        switch(column) {
            case LANGUAGE:
                items = courseData.getLanguages().findAll();
                break;
            case TITLE:
                items = courseData.getTitles().findAll();
                break;
            case MATERIAL:
                items = courseData.getMaterials().findAll();
                break;
            case COURSE_FIELD_TYPE:
            default:
                items = courseData.getCourseTypes().findAll();
        }

        model.addAttribute("title", "All " + column.getName() + " Values");
        model.addAttribute("column", column);
        model.addAttribute("items", items);

        return "list-column";
    }

    @RequestMapping(value = "jobs")
    public String listJobsByColumnAndValue(Model model,
            @RequestParam CourseFieldType column, @RequestParam String name) {

        ArrayList<Course> courses = courseData.findByColumnAndValue(column, name);

        model.addAttribute("title", "Course with " + column.getName() + ": " + name);
        model.addAttribute("courses", courses);

        return "list-jobs";
    }

    @RequestMapping(value = "all")
    public String listAllCourses(Model model) {

        ArrayList<Course> courses = courseData.findAll();

        model.addAttribute("title", "All Courses");
        model.addAttribute("courses", courses);

        return "list-courses";
    }
}
