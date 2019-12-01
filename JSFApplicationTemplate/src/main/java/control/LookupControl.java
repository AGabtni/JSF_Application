/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

import beans.CourseData;
import beans.TeamData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import persistence.DBHelper;
import persistence.Course;


@Named(value = "lookupControl")
@RequestScoped
public class LookupControl implements Serializable {
    @Inject
    private CourseData courseData;
    

    @PersistenceContext
    EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    
    /**
     * Creates a new instance of LookupControl
     */
    public LookupControl() {
    }
    public void lookup() {
       List<Course> results = new ArrayList();
       if (!"".equals(courseData.getCourseCode())) {
            // lookup by id
            results = getCourseById(em,courseData);
       }
       courseData.setLookupResults(results);
    }
    
    //Called on page load
    public void displayCourses(){
        
        List<Course> courses = DBHelper.findCourses(em);
        courseData.setLookupResults(courses);
        courseData.setAddstatus(courses.size()+" courses found .");
        
    }
    public void add() {
        if (DBHelper.addCourse(em,utx,courseData)) {
            courseData.setAddstatus("The User Was Successfuly Added");
        } else {
            courseData.setAddstatus("Addition of the User Failed");
        }
    }
    
    
    
        /**
     * Find a user by id and check if any that the other fields are valid
     */
    private List<Course> getCourseById(EntityManager em,CourseData userData) {
        ArrayList<Course> result = new ArrayList<>();
        Course course = DBHelper.findCourse(em,userData.getCourseCode());
        if (course != null && course.matches(userData)) {
            result.add(course);  
        }
        return result;  
    }
    
   
    
//    private List<Course> checkResults(List<Course> allresults,CourseData userData) {
//        ArrayList<Course> results = new ArrayList<>();
//        for (Course user: allresults) {
//            if (user.matches(userData)) results.add(user);
//        }
//        return results;
//    }
}
