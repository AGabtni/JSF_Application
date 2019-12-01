/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import persistence.Course;


@Named(value = "courseData")
@RequestScoped
public class CourseData {
    private String courseCode;
    private String addstatus;
    private List<Course> lookupResults;
    /**
     * Creates a new instance of UserData
     */
    public CourseData() {
    }

    /**
     * @return the courseCode
     */
    public String getCourseCode() {
        return courseCode;
    }
    
    
    /**
     * @param courseCode the courseCode to set
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public List<Course> getLookupResults() {
        return lookupResults;
    }
    public void setLookupResults(List<Course> results) {
        this.lookupResults = results;
    }
    
    public String getAddstatus() {
        return addstatus;
    }

    public void setAddstatus(String addstatus) {
        this.addstatus = addstatus;
    }
    
    
    // show results if any
    public boolean getShowResults() {
        return (lookupResults != null) && !lookupResults.isEmpty();
    }
    // show message if no result
    public boolean getShowMessage() {
        return (lookupResults != null) && lookupResults.isEmpty();
    }
}
