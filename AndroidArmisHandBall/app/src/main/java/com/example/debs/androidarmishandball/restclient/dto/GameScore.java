package com.example.debs.androidarmishandball.restclient.dto;

public class GameScore {

    private int gamePk ;
    private byte homeTeamScore ;
    private byte visitorTeamScore ;

    public GameScore(){
    }

    public GameScore(int gamePk , byte homeTeamScore , byte visitorTeamScore){
        this.gamePk = gamePk;
        this.homeTeamScore = homeTeamScore;
        this.visitorTeamScore = visitorTeamScore;
    }

    public int getGamePk() {
        return gamePk;
    }

    public void setGamePk(int gamePk) {
        this.gamePk = gamePk;
    }

    public byte getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(byte homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public byte getVisitorTeamScore() {
        return visitorTeamScore;
    }

    public void setVisitorTeamScore(byte visitorTeamScore) {
        this.visitorTeamScore = visitorTeamScore;
    }
}
