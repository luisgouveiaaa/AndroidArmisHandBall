package com.example.debs.androidarmishandball.restclient.dto;

import com.example.debs.androidarmishandball.restclient.dto.Athlete;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Luis Gouveia on 16/08/2017.
 */

public class Team implements Serializable{

    private int pk;
    private String gender;
    private String ageRange;
    private int clubPk;

    public Team(){
    }

    public Team(int pk, String gender , String ageRange , int clubPk) {
        this.pk = pk;
        this.gender = gender;
        this.ageRange = ageRange;
        this.clubPk = clubPk;
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(String ageRange) {
        this.ageRange = ageRange;
    }

    public int getClubPk() {
        return clubPk;
    }

    public void setClubPk(int clubPk) {
        this.clubPk = clubPk;
    }
}
