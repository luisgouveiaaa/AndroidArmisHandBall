package com.example.debs.androidarmishandball;

/**
 * Created by Luis Gouveia on 16/08/2017.
 */

public class Edition {

    private String season;
    private Tournament tournament;

    public Edition(String season, Tournament tournament) {
        this.season = season;
        this.tournament = tournament;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }
}
