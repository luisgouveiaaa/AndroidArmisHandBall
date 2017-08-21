package com.example.debs.androidarmishandball;

import android.support.annotation.Nullable;

import java.util.Date;

/**
 * Created by Luis Gouveia on 16/08/2017.
 */

public class Game {

    private int pk;
    private String mHomeTeam;
    private String mVisitorTeam;
    private String mMatchDay;
    private String date;
    private byte mHomeTeamScore;
    private byte mVisitorTeamScore;

    public Game(int pk, String matchDay,String visitorClubLogo,String homeClubLogo,
                String visitorClubName, String homeClubName, byte visitorTeamResult,
                 byte homeTeamResult, int visitorTeamPk, int homeTeamPk, String date) {
        this.pk = pk;
        this.mHomeTeam = homeClubName;
        this.mVisitorTeam = visitorClubName;
        this.mMatchDay = matchDay;
        this.mHomeTeamScore = homeTeamResult;
        this.mVisitorTeamScore = visitorTeamResult;
        this.date = date;

    }

    public String getHomeTeam() {
        return mHomeTeam;
    }

    public void setHomeTeam(String mHomeTeam) {
        this.mHomeTeam = mHomeTeam;
    }

    public String getVisitorTeam() {
        return mVisitorTeam;
    }

    public void setVisitorTeam(String mVisitorTeam) {
        this.mVisitorTeam = mVisitorTeam;
    }

    public String getMatchDay() {
        return mMatchDay;
    }

    public void setMatchDay(String mMatchDay) {
        this.mMatchDay = mMatchDay;
    }

    public byte getHomeTeamScore() {
        return mHomeTeamScore;
    }

    public void setHomeTeamScore(byte mHomeTeamScore) {
        this.mHomeTeamScore = mHomeTeamScore;
    }

    public byte getVisitorTeamScore() {
        return mVisitorTeamScore;
    }

    public void setVisitorTeamScore(byte mVisitorTeamScore) {
        this.mVisitorTeamScore = mVisitorTeamScore;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
