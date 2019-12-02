/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;
import beans.TeamData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Ahmed
 */
@Entity
public class Team implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String teamName;
    @OneToMany
    private List<UserAccount> members;
    private TeamParams parameters;
    @ManyToOne
    private Course course;
    
    public Team(){
        
        this.members = new ArrayList<>();
        
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (teamName != null ? teamName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Team)) {
            return false;
        }
        Team other = (Team) object;
        if ((this.teamName == null && other.teamName != null) || (this.teamName != null && !this.teamName.equals(other.teamName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.Team[ teamName=" + teamName + " ]";
    }
    
     /**
     * 
     * @param userData
     * @return true if this User matches the userData bean*/
     
    public boolean matches(TeamData teamData) {
        if (!"".equals(teamData.getTeamName()) && !this.getTeamName().trim().equals(teamData.getTeamName().trim())) {
            return false;
        }
        return true;
    }
    
    public List<UserAccount> getMembers(){
        
        return this.members;
    }
    
    public void setMembers(UserAccount user){
        
        this.members.add(user);
    }
    
    
    public TeamParams getParameters(){
        
        return this.parameters;
    }
    
    public void setParameters(TeamParams params){
        
        this.parameters = params;
    }
    
    
    public Course getCourse(){
        
        return this.course;
    }
    
    
    public void setCourse(Course course){
        
        this.course = course;
    }
    
    
    
}
