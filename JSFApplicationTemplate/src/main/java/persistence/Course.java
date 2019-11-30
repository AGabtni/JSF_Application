/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import beans.CourseData;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import persistence.TeamParams;
/**
 *
 * @author Marc_
 */
@Entity
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String courseCode;
    
    //new instance of team parameters associated with this course
    private TeamParams teamParams = null;
    
    
    
    public Course(){
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (courseCode != null ? courseCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Course)) {
            return false;
        }
        Course other = (Course) object;
        if ((this.courseCode == null && other.courseCode != null) || (this.courseCode != null && !this.courseCode.equals(other.courseCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.Course[ courseCode=" + courseCode + " ]";
    }
    
     /**
     * 
     * @param userData
     * @return true if this User matches the userData bean
     */
    public boolean matches(CourseData courseData) {
        if (!"".equals(courseData.getCourseCode()) && !this.getCourseCode().trim().equals(courseData.getCourseCode().trim())) {
            return false;
        }
        return true;
    }
    
    
    //New functions added : 
    public TeamParams getTeamParams() {
        return this.teamParams;
    }

    public void setTeamParams(TeamParams teamParams) {
        this.teamParams = teamParams;
    }
    /*-----------------------*/
    
    
    
    
}
