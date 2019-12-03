/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import control.StudenOperationsController;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import persistence.Team;
/**
 *
 * @author Ahmed
 */

@Named(value = "teamData")
@RequestScoped
public class TeamData {
    private String teamName;
    private String addstatus;
    private List<Team> teams;
    private String userId;
    private String selectedTeam;
    private String selectedCourse;
    
     @Inject
        private StudenOperationsController studentController;
     
     
    public TeamData() {
    }

    /**
     * @return the teamName
     */
    public String getTeamName() {
        return this.teamName;
    }

    /**
     * @param teamName 
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    
    public String getAddstatus() {
        return addstatus;
    }

    public void setAddstatus(String addstatus) {
        this.addstatus = addstatus;
    }
    
    public List<Team> getTeamsFound() {
        return teams;
    }
    
    
    public void setTeamsFound(List<Team> teams) {
        this.teams = teams;
    }
    public boolean getShowResults() {
        return (teams != null) && !teams.isEmpty();
    }
    
   
    
    public void setUserId(String id){
        
        this.userId = id;
    }
    public String getUserId(){
        
        return this.userId;
    }
    
    public void setSelectedTeam(String  selected){
        this.selectedTeam = selected;
        studentController.join();

    }
    public String getSelectedTeam(){
        
        return this.selectedTeam;
    }
    
    public String getSelectedCourse(){
        
        return this.selectedCourse;
    }
    public void setSelectedCourse(String  selected){
        this.selectedCourse = selected;

    }
    
   
    
  
   
}
