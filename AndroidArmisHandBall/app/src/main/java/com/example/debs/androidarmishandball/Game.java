package com.example.debs.androidarmishandball;

import java.util.Date;

/**
 * Created by Luis Gouveia on 16/08/2017.
 */

public class Game {

    private Team mHomeTeam;
    private Team mVisitorTeam;
    private MatchDay mMatchDay;
    private Date date;
    private byte mHomeTeamScore;
    private byte mVisitorTeamScore;

    public Game(Team mHomeTeam, Team mVisitorTeam, MatchDay mMatchDay, byte mHomeTeamScore, byte mVisitorTeamScore) {
        this.mHomeTeam = mHomeTeam;
        this.mVisitorTeam = mVisitorTeam;
        this.mMatchDay = mMatchDay;
        this.mHomeTeamScore = mHomeTeamScore;
        this.mVisitorTeamScore = mVisitorTeamScore;
    }

    public Team getHomeTeam() {
        return mHomeTeam;
    }

    public void setHomeTeam(Team mHomeTeam) {
        this.mHomeTeam = mHomeTeam;
    }

    public Team getVisitorTeam() {
        return mVisitorTeam;
    }

    public void setVisitorTeam(Team mVisitorTeam) {
        this.mVisitorTeam = mVisitorTeam;
    }

    public MatchDay getMatchDay() {
        return mMatchDay;
    }

    public void setMatchDay(MatchDay mMatchDay) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
