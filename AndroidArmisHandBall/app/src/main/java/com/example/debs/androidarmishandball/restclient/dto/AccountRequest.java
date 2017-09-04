package com.example.debs.androidarmishandball.restclient.dto;


public class AccountRequest {

    private String Email;

    public AccountRequest(){
    }

    public AccountRequest(String Email){
        this.Email = Email;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
