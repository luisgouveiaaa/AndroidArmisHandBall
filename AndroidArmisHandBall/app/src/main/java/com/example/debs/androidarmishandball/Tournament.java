package com.example.debs.androidarmishandball;

/**
 * Created by Luis Gouveia on 16/08/2017.
 */

public class Tournament {

    private String name;
    private String gender;
    private AgeRange ageRange;
    private byte[] logo;
    private String type;

    public Tournament(String name, String gender, AgeRange ageRange, byte[] logo, String type) {
        this.name = name;
        this.gender = gender;
        this.ageRange = ageRange;
        this.logo = logo;
        this.type = type;
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

    public AgeRange getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(AgeRange ageRange) {
        this.ageRange = ageRange;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
