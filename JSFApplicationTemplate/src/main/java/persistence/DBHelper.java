package persistence;

import beans.CourseData;
import beans.TeamData;
import beans.TeamParamsData;


import persistence.Course;
import persistence.TeamParams;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;


public class DBHelper {
    
    
    //find User
    public static UserAccount findUser(EntityManager em, String userId){
        UserAccount u = em.find(UserAccount.class, userId);
        return u;
    } 
    
    //Course related db functions
    public static Course findCourse(EntityManager em,String courseCode) {
        Course u = em.find(Course.class, courseCode);
        return u;
    }
    
    public static List<Course> findCourses(EntityManager em) {
        TypedQuery<Course> query;
        query = em.createQuery("SELECT c FROM Course c", Course.class);
        List<Course> results = query.getResultList();
        return results;
    }
    
    
    public static boolean addCourse(EntityManager em, UserTransaction utx, CourseData courseData) {
        try {
            utx.begin();
            Course nCourse = new Course();
            nCourse.setCourseCode(courseData.getCourseCode());
            em.persist(nCourse);
            utx.commit();
            return true;
        } catch (IllegalArgumentException | NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            ex.printStackTrace();
        }
        return false;
    }
   
    //Team related db functions
    /**
     * fetch team from database and returns it
     * @param em
     * @param teamName
     * @return Team
     */
    public static Team findTeam(EntityManager em,String teamName) {
        Team u = em.find(Team.class, teamName);
        return u;
    }
    
    public static List<Team> findTeams(EntityManager em){
        
        TypedQuery<Team> query;
        query = em.createQuery("SELECT team FROM Team team", Team.class);
        List<Team> results = query.getResultList();
        return results;
    }
    
   /**
    * Add new Team
    * @param em
    * @param utx
    * @param teamData
    * @return 
    */
   public static boolean addTeam(EntityManager em, UserTransaction utx, TeamData teamData, String courseId) {
        try {
             
            utx.begin();
            UserAccount creator = em.find(UserAccount.class, teamData.getUserId());
            Course selectedCourse = em.find(Course.class, courseId);
            
            if(selectedCourse.getTeamParams() == null)
                return false;
            if(creator.getTeam() != null){
                
                if(creator.getTeam().getCourse().equals(selectedCourse)){
                    
                    return false;
                }
            }
            
            Team newTeam = new Team();
            //Team.setMinimumMembers(teamData.)
            
            newTeam.setTeamName(teamData.getTeamName());
            newTeam.setMembers(creator);
            newTeam.setCourse(selectedCourse);
            newTeam.setParameters(selectedCourse.getTeamParams());
            creator.setTeam(newTeam);
            
            
            em.persist(newTeam);
            utx.commit();
            return true;
        } catch (IllegalArgumentException | NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            ex.printStackTrace();
        }
        return false;
    }
   
   //Team parameters related functions 
   
   //1- create new team parameters
   //2 - set up parameters and associate course to new parameters 
   //3 - associate course with new instance of the team parameters
   /**
    * add new Team Params and create association with "Course"
    * @param em
    * @param utx
    * @param teamParamsData
    * @param course
    * @return 
    */
   public static boolean addTeamParams(EntityManager em, UserTransaction utx, TeamParamsData teamParamsData ){
   
       try {
           
            utx.begin();
            TeamParams newParams = new TeamParams();
            Course newCourse = em.find(Course.class, teamParamsData.getCourseCode());
            newParams.modifyParams(teamParamsData,newCourse);
            newCourse.setTeamParams(newParams);
            em.persist(newParams);
            utx.commit();
            return true;
        } catch (IllegalArgumentException | NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            ex.printStackTrace();
        }
        return false;
       
       
   }
   
   
   
   
   
   
   
   
   
   
//    public static List findUsersByName(EntityManager em,String name) {
//        Query query = em.createQuery(
//                "SELECT u FROM User u" +
//                " WHERE u.NAME = :userName");
//        query.setParameter("userName",name);
//        return performQuery(query);
//    }
//    
//    private static List performQuery(final Query query) {
//        List resultList = query.getResultList();
//        if (resultList.isEmpty()) {
//            return null;
//        } 
//        ArrayList<Course> results = new ArrayList<>();
//        results.addAll(resultList);
//        return results;
//    }

   
   
   
}
