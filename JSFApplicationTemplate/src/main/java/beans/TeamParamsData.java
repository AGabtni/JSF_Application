/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import persistence.Course;

/**
 *
 * @author Ahmed
 */
@Named(value = "teamParamsData")
@RequestScoped
public class TeamParamsData {
    
    
    
     private int minStudents;
     private int maxStudents;
     private Course course;
     private String courseCode;
     private String submitMessage;
     
     /**
      * TeamParamsData constructor
      */
     public TeamParamsData(){
        
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
     
     public void minMaxValidator(FacesContext context, UIComponent comp,
			Object value){
         int num = (int) value;
         
         if(num <= 0){
             ((UIInput) comp).setValid(false);
             FacesMessage message = new FacesMessage("Minimum must be 1");
            context.addMessage(comp.getClientId(context), message);
         }
             
             
         
     }
     
     /**
      function helpers for the course
      
      */
     public void setCourseCode (String nCourse){
         
         
         this.courseCode = nCourse;
     }
     
     
     public String getCourseCode (){
         
         
         return this.courseCode ;
     }
 
     
     public void setSubmitMessage(String message){
     
         this.submitMessage = message;
     }
     
     public String getSubmitMessage(){
         
        return this.submitMessage ;
     }
     
}
