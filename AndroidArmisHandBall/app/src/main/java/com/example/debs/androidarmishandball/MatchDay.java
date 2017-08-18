package com.example.debs.androidarmishandball;

/**
 * Created by Luis Gouveia on 16/08/2017.
 */

public class MatchDay {

    private Edition edition;
    private byte number;
    private byte phase;

    public MatchDay(Edition edition, byte number, byte phase) {
        this.edition = edition;
        this.number = number;
        this.phase = phase;
    }

    public Edition getEdition() {
        return edition;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    public byte getNumber() {
        return number;
    }

    public void setNumber(byte number) {
        this.number = number;
    }

    public byte getPhase() {
        return phase;
    }

    public void setPhase(byte phase) {
        this.phase = phase;
    }
}
