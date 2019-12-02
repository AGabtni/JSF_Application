package control;

import beans.TeamData;
import beans.LoginBean;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import persistence.DBHelper;
import persistence.Team;



/**
 *
 * @author Ahmed
 */


@Named(value = "studentControl")
@RequestScoped
public class StudenOperationsController {
    
    
     @Inject
    private TeamData teamData;
     
     @Inject
     private LoginBean loginData;

    @PersistenceContext
    EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    private String selectedCourseCode;
    
    public StudenOperationsController(){
    }
    
    
    /**
     * 
     * Creates a new team (INCOMPLETE)
     */
    public void addTeam(){
        if(!teamData.getTeamName().equals("")){
            if( DBHelper.findTeam(em, teamData.getTeamName()) == null){
                
                if(DBHelper.addTeam(em,utx,teamData)){
                    loginData.setIsTeamMember(true);
                    teamData.setAddstatus("The Team Was Successfuly Added by " + teamData.getUserId());

                }else{
                    teamData.setAddstatus("Failed to add the Team");
                }
        
            }else{
                teamData.setAddstatus("Team name already in use");
            }
            
        }else{
            
            teamData.setAddstatus("Team name field cannot be empty !");
        }
        
       
        
    }
    
    public void displayTeams(){
        List<Team> teams = DBHelper.findTeams(em);
        
        teamData.setTeamsFound(teams);
        teamData.setAddstatus(teams.size() +" teams found ");
        
    }
    
    public void setSelectedCourse(String courseCode){
       
        this.selectedCourseCode = courseCode;
        System.out.println("Hey "+courseCode);
    }
    
    public String getSelectedCourse(){
        
        System.out.println(this.selectedCourseCode);
        return this.selectedCourseCode;
    }
    
    
}
