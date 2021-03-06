package com.example.debs.androidarmishandball.restclient.dto;

import android.support.annotation.Nullable;

import java.io.Serializable;

public class GoalRecord implements Serializable{

    private int pk ;
    private int athletePk;
    private int gamePk;
    private int team;
    private String athleteName ;
    private byte[] athletePhoto ;
    private byte count ;

    public GoalRecord(){

    }

    public GoalRecord(int pk, int team , int gamePk, String athleteName , byte[] athletePhoto , byte count)
    {
        this.pk = pk;
        this.team = team; //team is 0 to home, 1 to visitor and 2 if different
        this.athleteName = athleteName;
        this.athletePhoto = athletePhoto;
        this.count = count;
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public int getAhtletePk() {
        return athletePk;
    }

    public void setAthletePk(int athletePk) {
        this.athletePk = athletePk;
    }

    public int getTeam() {
        return team;
    }

    public int getGamePk() {
        return gamePk;
    }

    public void setGamePk(int gamePk) {
        this.gamePk = gamePk;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public String getAthleteName() {
        return athleteName;
    }

    public void setAthleteName(String athleteName) {
        this.athleteName = athleteName;
    }

    public byte[] getAthletePhoto() {
        return athletePhoto;
    }

    public void setAthletePhoto(byte[] athletePhoto) {
        this.athletePhoto = athletePhoto;
    }

    public byte getCount() {
        return count;
    }

    public void setCount(byte count) {
        this.count = count;
    }

}