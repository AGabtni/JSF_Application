/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;
import beans.TeamParamsData;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import persistence.Course;
/**
 *
 * @author Ahmed
 */

@Entity
public class TeamParams implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    private String courseCode;
    
    private int minStudents;
    private int maxStudents; 
    private Course course;
     
    
    public TeamParams(){
    
    }
    /**fefef
     * Setup/Modify the new parameters instance
     * @param dataParams
     * @param course 
     */
     public void modifyParams(TeamParamsData dataParams, Course course){
         
        this.minStudents = dataParams.getMinStudents();
        this.maxStudents = dataParams.getMaxStudents();
        this.courseCode = course.getCourseCode();
        this.course = course;
         
         
     }
     
    /**
     * 
     * 
     * Function helpers for min students
     */ 
    
     
     public void setMinStudents(int minStudents){
         this.minStudents = minStudents;
        
    }
     
     public int getMinStudents(){
         return this.minStudents;
        
    }
     
     
     /**
     * 
     * 
     * Function helpers for max students
     */ 
     
     public void setMaxStudents(int maxStudents){
         this.maxStudents = maxStudents;
        
    }
     
     public int getMaxStudents(){
         return this.maxStudents;
        
    }
     
     /**
      function helpers for the course
      
      */
     public void setCourse (Course nCourse){
         
         
         this.course = nCourse;
     }
     
     
     public String getCourse (){
         
        return  this.courseCode;
     }
}
