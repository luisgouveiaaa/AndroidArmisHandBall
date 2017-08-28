package com.example.debs.androidarmishandball.restclient.dto;

/**
 * Created by Luis Gouveia on 16/08/2017.
 */

public class Athlete {

    private int pk;
    private byte[] photo;
    private String name;
    private String gender;
    private int age;
    private String nationality;
    private String clubName;
    private int teamPk;

    public Athlete(){
    }

    public Athlete(int pk, byte[] photo , String name , String gender , int age , String nationality , String clubName , int teamPk){
        this.pk = pk;
        this.photo = photo;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.nationality = nationality;
        this.clubName = clubName;
        this.teamPk = teamPk;
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public int getTeamPk() {
        return teamPk;
    }

    public void setTeamPk(int teamPk) {
        this.teamPk = teamPk;
    }
}
