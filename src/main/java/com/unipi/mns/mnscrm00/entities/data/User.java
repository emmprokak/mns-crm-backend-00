package com.unipi.mns.mnscrm00.entities.data;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="CRMUser_ent")
public class User {
    @Id
    @UuidGenerator
    private String id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Task> tasks;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Case> cases;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="created_date")
    private Date creationDate;

    @Column(name="title")
    private Timestamp lastLogin;

    public User(Date creationDate, String email, String firstName, String id, Timestamp lastLogin, String lastName) {
        this.creationDate = creationDate;
        this.email = email;
        this.firstName = firstName;
        this.id = id;
        this.lastLogin = lastLogin;
        this.lastName = lastName;
    }

    public User() {}

    public List<Case> getCases() {
        return cases;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
