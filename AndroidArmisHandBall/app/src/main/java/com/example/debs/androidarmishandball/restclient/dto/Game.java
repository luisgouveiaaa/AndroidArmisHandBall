package com.example.debs.androidarmishandball.restclient.dto;

import java.io.Serializable;

/**
 * Created by Luis Gouveia on 16/08/2017.
 */

public class Game implements Serializable{

    private int pk;
    private String matchDay;
    private byte[] visitorClubLogo;
    private byte[] homeClubLogo;
    private String visitorClubName;
    private String homeClubName;
    private byte visitorTeamResult;
    private byte homeTeamResult;
    private int visitorTeamPk;
    private int homeTeamPk;
    private String date;

    public Game(){
    }

    public Game(int pk, String matchDay, byte[] visitorClubLogo ,byte[] homeClubLogo,
                String visitorClubName, String homeClubName, byte visitorTeamResult,
                 byte homeTeamResult, int visitorTeamPk, int homeTeamPk, String date) {
        this.pk = pk;
        this.matchDay = matchDay;
        this.visitorClubLogo = visitorClubLogo;
        this.homeClubLogo = homeClubLogo;
        this.visitorClubName = visitorClubName;
        this.homeClubName = homeClubName;
        this.visitorTeamResult = visitorTeamResult;
        this.homeTeamResult = homeTeamResult;
        this.visitorTeamPk = visitorTeamPk;
        this.homeTeamPk = homeTeamPk;
        this.date = date;
    }

    public int getPk() {
        return pk;
    }

    public int getHomeTeamPk() {
        return homeTeamPk;
    }

    public int getVisitorTeamPk() {
        return visitorTeamPk;
    }

    public byte[] getVisitorClubLogo() {
        return visitorClubLogo;
    }

    public void setVisitorClubLogo(byte[] visitorClubLogo) { this.visitorClubLogo = visitorClubLogo; }

    public byte[] getHomeClubLogo() {
        return homeClubLogo;
    }

    public void setHomeClubLogo(byte[] homeClubLogo) {
        this.homeClubLogo = homeClubLogo;
    }

    public String getHomeTeam() {
        return homeClubName;
    }

    public String getVisitorTeam() {
        return visitorClubName;
    }

    public String getMatchDay() {
        return matchDay;
    }

    public byte getHomeTeamScore() {
        return homeTeamResult;
    }

    public void setHomeTeamResult(byte homeTeamResult) {
        this.homeTeamResult = homeTeamResult;
    }

    public byte getVisitorTeamScore() {
        return visitorTeamResult;
    }

    public void setVisitorTeamResult(byte visitorTeamResult) { this.visitorTeamResult = visitorTeamResult; }

    public String getDate() {
        return date;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public void setHomeTeamPk(int homeTeamPk) {
        this.homeTeamPk = homeTeamPk;
    }

    public void setVisitorTeamPk(int visitorTeamPk) {
        this.visitorTeamPk = visitorTeamPk;
    }

    public void setHomeClubName(String homeClubName) {
        this.homeClubName = homeClubName;
    }

    public void setVisitorClubName(String visitorClubName) {
        this.visitorClubName = visitorClubName;
    }

    public void setMatchDay(String matchDay) {
        this.matchDay = matchDay;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
