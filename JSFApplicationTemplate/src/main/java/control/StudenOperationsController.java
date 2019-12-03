package control;

import beans.TeamData;
import beans.LoginBean;
import java.util.ArrayList;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import persistence.Course;
import persistence.DBHelper;
import persistence.Team;
import persistence.UserAccount;



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
    private LoginBean loginBean;

    @PersistenceContext
    EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    
    
    public StudenOperationsController(){
    }
    
    
    /**
     * 
     * Creates a new team (INCOMPLETE)
     */
    public void addTeam(){
        if(!teamData.getTeamName().equals("")){
            if( DBHelper.findTeam(em, teamData.getTeamName()) == null){
                
                if(DBHelper.addTeam(em,utx,teamData,loginBean.getSelectedCourse())){
                    loginBean.setIsTeamMember(true);
                    
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
        System.out.println("From display teams selected courrse is : " + loginBean.getSelectedCourse());
        Course selectedCourse = DBHelper.findCourse(em, loginBean.getSelectedCourse());
        if(selectedCourse.getTeams().isEmpty()){
            
            teamData.setAddstatus("No teams found ");

        }
        else{
            teamData.setTeamsFound(selectedCourse.getTeams());
            teamData.setAddstatus(selectedCourse.getTeams().size() +" teams found ");
        }
        

    }
    
    public void displayTeam(){
        
        String userId = teamData.getUserId();
        String selectedCourse = loginBean.getSelectedCourse();
        UserAccount student = DBHelper.findUser(em, userId);
        Team userTeam = student.getTeam();
        //Changes must be done here for application thing
        if(userTeam != null){
            if(!userTeam.getCourse().getCourseCode().equals(selectedCourse)){
                teamData.setAddstatus("You do not have or belong to a team in this course");
            }else{
                List<Team> teams = new ArrayList<>(List.of(userTeam));
                teamData.setTeamsFound(teams);
                teamData.setAddstatus("Your team : " + student.getTeam().getTeamName());
            }
        }else{
            teamData.setAddstatus("You have no teams");
        }
    }
    
    public void join(){
        
        teamData.setSelectedCourse(loginBean.getSelectedCourse());
        teamData.setAddstatus(DBHelper.joinTeam(em, utx, teamData));


        
    }
    
    
}
