package org.saranya.models.data;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.saranya.models.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CourseDataImporter {

    private static final String DATA_FILE = "course_data.csv";
    private static boolean isDataLoaded = false;

    /**
     * Read in data from a CSV file and store it in a list
     */
    static void loadData(CourseData courseData) {

        // Only load data once
        if (isDataLoaded) {
            return;
        }

        try {

            // Open the CSV file and set up pull out column header info and records
            Resource resource = new ClassPathResource(DATA_FILE);
            InputStream is = resource.getInputStream();
            Reader reader = new InputStreamReader(is);
            CSVParser parser = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
            List<CSVRecord> records = parser.getRecords();
            Integer numberOfColumns = records.get(0).size();
            String[] headers = parser.getHeaderMap().keySet().toArray(new String[numberOfColumns]);

            // Put the records into a more friendly format
            for (CSVRecord record : records) {
                String langStr = record.get("language");
                String titleStr = record.get("title");
                String matStr = record.get("material");
                String courtypStr = record.get("coursetype");
                /*
                String empStr = record.get("employer");
                String locStr = record.get("location");
                String coreCompStr = record.get("core competency");
                String posTypeStr = record.get("position type");
*/
                Language lan = courseData.getLanguages().findByValue(langStr);
                if (lan == null) {
                    lan = new Language(langStr);
                    courseData.getLanguages().add(lan);
                }
                /*
                Employer emp = jobData.getEmployers().findByValue(empStr);
                if (emp == null) {
                    emp = new Employer(empStr);
                    jobData.getEmployers().add(emp);
                }
*/
                Title tit = courseData.getTitles().findByValue(titleStr);
                if (tit == null) {
                    tit = new Title(titleStr);
                    courseData.getTitles().add(tit);
                }
                /*
                Location loc = jobData.getLocations().findByValue(locStr);
                if (loc == null) {
                    loc = new Location(locStr);
                    jobData.getLocations().add(loc);
                }
                */
                Material mat = courseData.getMaterials().findByValue(matStr);
                if(mat == null){
                    mat = new Material(matStr);
                    courseData.getMaterials().add(mat);
                }
                /*
                PositionType posType = jobData.getPositionTypes().findByValue(posTypeStr);
                if (posType == null) {
                    posType = new PositionType(posTypeStr);
                    jobData.getPositionTypes().add(posType);
                }
*/
                CourseType courtyp = courseData.getCourseTypes().findByValue(courtypStr);
                if(courtyp == null){
                    courtyp = new CourseType(courtypStr);
                    courseData.getCourseTypes().add(courtyp);
                }
                /*
                CoreCompetency coreComp = jobData.getCoreCompetencies().findByValue(coreCompStr);
                if (coreComp == null) {
                    coreComp = new CoreCompetency(coreCompStr);
                    jobData.getCoreCompetencies().add(coreComp);
                }
*/
                Course newCourse = new Course(record.get("name"), lan, tit, mat,courtyp);
 //               Job newJob = new Job(record.get("name"), emp, loc, posType, coreComp);
                courseData.add(newCourse);
                //jobData.add(newJob);
            }

            // flag the data as loaded, so we don't do it twice
            isDataLoaded = true;

        } catch (IOException e) {
            System.out.println("Failed to load job data");
            e.printStackTrace();
        }
    }

}
