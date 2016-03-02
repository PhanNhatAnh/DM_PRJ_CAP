package model.pojo;
// Generated Feb 28, 2016 2:13:37 PM by Hibernate Tools 4.3.1


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * User generated by hbm2java
 */
public class Users  implements java.io.Serializable {


     private int userId;
     private Department department;
     private Role role;
     private String userName;
     private String firstAndLastName;
     private String password;
     private Set<Department> departments = new HashSet<Department>(0);
     private Set<Document> documents = new HashSet<Document>(0);

    public Users() {
    }

	
    public Users(int userId, String userName, String password, String firstAndLastName) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.firstAndLastName = firstAndLastName;
    }
    public Users(int userId, Department department, Role role, String userName, String password, String firstAndLastName, Set<Department> departments, Set<Document> documents) {
       this.userId = userId;
       this.department = department;
       this.role = role;
       this.userName = userName;
       this.password = password;
       this.firstAndLastName = firstAndLastName;
       this.departments = departments;
       this.documents = documents;
    }
   
    public int getUserId() {
        return this.userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public Department getDepartment() {
        return this.department;
    }
    
    public void setDepartment(Department department) {
        this.department = department;
    }
    public Role getRole() {
        return this.role;
    }
    
    public void setRole(Role role) {
        this.role = role;
    }
    public Serializable getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Serializable getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public Set getDepartments() {
        return this.departments;
    }
    
    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }
    public Set getDocuments() {
        return this.documents;
    }
    
    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }
    
    public String getFirstAndLastName() {
        return this.firstAndLastName;
    }
    
    public void setFirstAndLastName(String firstAndLastName) {
        this.firstAndLastName = firstAndLastName;
    }
}


