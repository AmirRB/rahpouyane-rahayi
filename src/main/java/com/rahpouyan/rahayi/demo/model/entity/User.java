package com.rahpouyan.rahayi.demo.model.entity;

import javax.persistence.*;

@Entity(name = "User")
@Table(name = "userresource")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator = "seqUser")
    @SequenceGenerator(name = "seqUser", initialValue = 1, sequenceName = "seqUser", allocationSize = 1)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;
    private String role;

    public User() {
    }


    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
