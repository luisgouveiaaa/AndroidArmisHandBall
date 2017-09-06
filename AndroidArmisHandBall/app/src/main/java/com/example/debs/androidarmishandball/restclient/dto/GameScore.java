package com.example.debs.androidarmishandball.restclient.dto;

public class GameScore {

    private int gamePk ;
    private int teamPk ;
    private byte teamScore ;

    public GameScore(){
    }

    public GameScore(int gamePk , int teamPk , byte teamScore){
        this.gamePk = gamePk;
        this.teamPk = teamPk;
        this.teamScore = teamScore;
    }

    public int getGamePk() {
        return gamePk;
    }

    public void setGamePk(int gamePk) {
        this.gamePk = gamePk;
    }

    public int getTeamPk() {
        return teamPk;
    }

    public void setTeamPk(int teamPk) {
        this.teamPk = teamPk;
    }

    public byte getTeamScore() {
        return teamScore;
    }

    public void setTeamScore(byte teamScore) {
        this.teamScore = teamScore;
    }
}
