package com.example.debs.androidarmishandball.restclient.dto;

public class PostedGoalRecord {

    private int pk;
    private int athletepk;
    private int gamepk;
    private int teampk;
    private byte count;

    public PostedGoalRecord(){

    }

    public PostedGoalRecord(int pk,int athletepk , int gamepk , int teampk , byte count){
        this.pk = pk;
        this.athletepk = athletepk;
        this.gamepk = gamepk;
        this.teampk = teampk;
        this.count = count;
    }

    public int getPk(){
        return pk;
    }

    public void setPk(int pk){
        this.pk = pk;
    }

    public int getAthletepk() {
        return athletepk;
    }

    public void setAthletepk(int athletepk) {
        this.athletepk = athletepk;
    }

    public int getGamepk() {
        return gamepk;
    }

    public void setGamepk(int gamepk) {
        this.gamepk = gamepk;
    }

    public int getTeampk() {
        return teampk;
    }

    public void setTeampk(int teampk) {
        this.teampk = teampk;
    }

    public byte getCount() {
        return count;
    }

    public void setCount(byte count) {
        this.count = count;
    }
}
