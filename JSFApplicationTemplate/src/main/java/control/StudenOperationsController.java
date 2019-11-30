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


@Named(value = "studentControl")
@RequestScoped

/**
 *
 * @author Ahmed
 */
public class StudenOperationsController {
    
}
