package com.example.debs.androidarmishandball;

import java.util.List;

/**
 * Created by Luis Gouveia on 16/08/2017.
 */

public class Team {

    private Club mClub;
    private String mGender;
    private AgeRange mAgeRange;
    private List<Athlete> mAthletes;

    public Team(Club mClub, String mGender, AgeRange mAgeRange, List<Athlete> mAthletes) {
        this.mClub = mClub;
        this.mGender = mGender;
        this.mAgeRange = mAgeRange;
        this.mAthletes = mAthletes;
    }

    public Club getClub() {
        return mClub;
    }

    public void setClub(Club mClub) {
        this.mClub = mClub;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String mGender) {
        this.mGender = mGender;
    }

    public AgeRange getAgeRange() {
        return mAgeRange;
    }

    public void setAgeRange(AgeRange mAgeRange) {
        this.mAgeRange = mAgeRange;
    }

    public List<Athlete> getAthletes() {
        return mAthletes;
    }

    public void setAthletes(List<Athlete> mAthletes) {
        this.mAthletes = mAthletes;
    }
}
