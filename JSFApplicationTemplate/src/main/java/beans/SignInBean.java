/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import persistence.UserAccount;

/**
 *
 * @author ssome
 */
@Named(value = "signInBean")
@RequestScoped
public class SignInBean {

    private String userId;
    private String firstname;
    private String lastname;
    private String password;
    private String email;
    private String role;

    @PersistenceContext(unitName = "HalfFull_TeamProject_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    private String status;

    /**
     * Creates a new instance of SignInBean
     */
    public SignInBean() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the status message
     */
    public String getStatus() {
        return status;
    }

    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

    public String addUser() {
        try {
            UserAccount acc = new UserAccount();
            acc.setUserId(userId);
            acc.setFirstname(firstname);
            acc.setLastname(lastname);
            acc.setEmail(email);
            acc.setIsStudent(false);
            acc.setIsTA(false);
            acc.setIsTeacher(false);

            if (role.equalsIgnoreCase("1")) {
                acc.setIsStudent(true);
            } else if (role.equalsIgnoreCase("2")){
                acc.setIsTA(true);
            } else if (role.equalsIgnoreCase("3")) {
                acc.setIsTeacher(true);
            }
            // randomly generate salt value
            final Random r = new SecureRandom();
            byte[] salt = new byte[32];
            r.nextBytes(salt);
            String saltString = new String(salt, "UTF-8");
            // hash password using SHA-256 algorithm
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String saltedPass = saltString + password;
            byte[] passhash = digest.digest(saltedPass.getBytes("UTF-8"));
            acc.setSalt(salt);
            acc.setPassword(passhash);
            persist(acc);
            status = "New Account Created Fine";
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.setAttribute("User", acc);
            return "registered";
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | RuntimeException ex) {
            Logger.getLogger(SignInBean.class.getName()).log(Level.SEVERE, null, ex);
            status = "Error While Creating New Account";
            return "failure";
        }
    }

}
