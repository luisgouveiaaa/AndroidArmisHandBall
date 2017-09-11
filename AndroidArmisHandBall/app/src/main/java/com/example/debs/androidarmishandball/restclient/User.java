package com.example.debs.androidarmishandball.restclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;



@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable {
    private String username;
    private String email;
    private String name;
    private String password;

    public User() {
    }

    public User(String username, String password, String email, String name) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public boolean hasRole(String role){
        for(String r : roles){
            if(r.equalsIgnoreCase(role)){
                return true;
            }
        }
        return false;
    }*/
}
