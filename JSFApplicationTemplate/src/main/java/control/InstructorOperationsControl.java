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
import persistence.Team;

import beans.TeamParamsData;

/**
 *
 * @author Ahmed
 */


@Named(value = "instructorControl")
@RequestScoped
public class InstructorOperationsControl {
    @Inject
    private TeamParamsData teamParamsData;
    
    @PersistenceContext
    EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    
    public void setupTeamParams(){
        //if(teamParamsData.)
        System.out.println("Settin up new team parameters");
        String courseCode = teamParamsData.getCourseCode();
        Course course = DBHelper.findCourse(em, courseCode);
        if(course != null ){
            System.out.println(" course.getTeamParams() " + course.getCourseCode());
            //clSystem.out.println(" course.getTeamParams() " + course.getTeamParams());
            
            if(course.getTeamParams() != null){
                teamParamsData.setSubmitMessage("Course << "+ courseCode + " >> already has team parameters set up" );
            }else{
                 
                if(!DBHelper.addTeamParams(em, utx, teamParamsData)){    
                    
                    teamParamsData.setSubmitMessage("Can't add to db" );
                }else{    
                    
                    teamParamsData.setSubmitMessage("Sucess setting up the team params" );
                }
                
            }
            //Check if course already has teamParameters : 
            
        }
        else{
            
            teamParamsData.setSubmitMessage("No course found associated with the code << "+ courseCode+" >>");
        }
        
    }
    
    
}
