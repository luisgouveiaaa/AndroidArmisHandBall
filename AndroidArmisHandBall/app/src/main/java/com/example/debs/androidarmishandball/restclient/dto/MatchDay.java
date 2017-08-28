package com.example.debs.androidarmishandball.restclient.dto;

/**
 * Created by Luis Gouveia on 16/08/2017.
 */

public class MatchDay {

    private int pk;
    private String name;

    public MatchDay(){
    }

    public MatchDay(int pk, String name) {
        this.pk = pk;
        this.name = name;
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
