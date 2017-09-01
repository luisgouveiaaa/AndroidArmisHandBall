package com.example.debs.androidarmishandball.restclient.dto;

import java.io.Serializable;

/**
 * Created by Luis Gouveia on 16/08/2017.
 */

public class Edition implements Serializable{

    private int pk;
    private String season;

    public Edition(){
    }

    public Edition(int pk, String season) {
        this.season = season;
        this.pk = pk;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }
}
