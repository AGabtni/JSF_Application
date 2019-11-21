package persistence;

import beans.CourseData;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;


public class DBHelper {
    public static Course findCourse(EntityManager em,String courseCode) {
        Course u = em.find(Course.class, courseCode);
        return u;
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
}
