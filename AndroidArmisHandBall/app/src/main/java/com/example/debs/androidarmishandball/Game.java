package com.example.debs.androidarmishandball;

/**
 * Created by Luis Gouveia on 16/08/2017.
 */

public class Game {

    private int pk;
    private int mHomeTeamPk;
    private int mVisitorTeamPk;
    private String mHomeTeam;
    private String mVisitorTeam;
    private String mMatchDay;
    private String date;
    private byte mHomeTeamScore;
    private byte mVisitorTeamScore;
    private byte[] mVisitorClubLogo;
    private byte[] mHomeClubLogo;
    public Game(){

    }

    public Game(int pk, String matchDay, byte[] visitorClubLogo ,byte[] homeClubLogo,
                String visitorClubName, String homeClubName, byte visitorTeamResult,
                 byte homeTeamResult, int visitorTeamPk, int homeTeamPk, String date) {
        this.pk = pk;
        this.mHomeTeamPk = homeTeamPk;
        this.mVisitorTeamPk = visitorTeamPk;
        this.mHomeTeam = homeClubName;
        this.mVisitorTeam = visitorClubName;
        this.mMatchDay = matchDay;
        this.mHomeTeamScore = homeTeamResult;
        this.mVisitorTeamScore = visitorTeamResult;
        this.date = date;
        this.mVisitorClubLogo = visitorClubLogo;
        this.mHomeClubLogo = homeClubLogo;
    }

    public int getPk() {
        return pk;
    }

    public int getHomeTeamPk() {
        return mHomeTeamPk;
    }


    public int getVisitorTeamPk() {
        return mVisitorTeamPk;
    }


    public byte[] getVisitorClubLogo() {
        return mVisitorClubLogo;
    }

    public void setVisitorClubLogo(byte[] visitorClubLogo) {
        this.mVisitorClubLogo = visitorClubLogo;
    }

    public byte[] getHomeClubLogo() {
        return mHomeClubLogo;
    }

    public void setHomeClubLogo(byte[] homeClubLogo) {
        this.mHomeClubLogo = homeClubLogo;
    }

    public String getHomeTeam() {
        return mHomeTeam;
    }

    public String getVisitorTeam() {
        return mVisitorTeam;
    }

    public String getMatchDay() {
        return mMatchDay;
    }

    public byte getHomeTeamScore() {
        return mHomeTeamScore;
    }

    public void setHomeTeamResult(byte mHomeTeamScore) {
        this.mHomeTeamScore = mHomeTeamScore;
    }

    public byte getVisitorTeamScore() {
        return mVisitorTeamScore;
    }

    public void setVisitorTeamResult(byte mVisitorTeamScore) {
        this.mVisitorTeamScore = mVisitorTeamScore;
    }
    public String getDate() {
        return date;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public void setHomeTeamPk(int mHomeTeamPk) {
        this.mHomeTeamPk = mHomeTeamPk;
    }

    public void setVisitorTeamPk(int mVisitorTeamPk) {
        this.mVisitorTeamPk = mVisitorTeamPk;
    }

    public void setHomeClubName(String mHomeClubName) {
        this.mHomeTeam = mHomeClubName;
    }

    public void setVisitorClubName(String mVisitorTeam) {
        this.mVisitorTeam = mVisitorTeam;
    }

    public void setMatchDay(String mMatchDay) {
        this.mMatchDay = mMatchDay;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
