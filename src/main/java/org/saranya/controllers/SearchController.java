package org.saranya.controllers;

import org.saranya.models.Course;
import org.saranya.models.CourseFieldType;
import org.saranya.models.forms.SearchForm;
import org.saranya.models.data.CourseData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;


@Controller
@RequestMapping("search")
public class SearchController {

    private CourseData courseData = CourseData.getInstance();

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute(new SearchForm());
        return "search";
    }

    @RequestMapping(value = "results")
    public String search(Model model, @ModelAttribute SearchForm searchForm) {

        ArrayList<Course> courses;

        if (searchForm.getSearchField().equals(CourseFieldType.ALL)) {
            courses = courseData.findByValue(searchForm.getKeyword());
        } else {
            courses = courseData.findByColumnAndValue(searchForm.getSearchField(), searchForm.getKeyword());
        }

        model.addAttribute("courses", courses);

        return "search";
    }

}
