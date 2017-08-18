package com.example.debs.androidarmishandball;

/**
 * Created by Luis Gouveia on 16/08/2017.
 */

public class Club {

    private String name;
    private byte[] logo;
    private String taxNumber;
    private Nationality nationality;
    private boolean isNational;

    public Club(String name, byte[] logo, String taxNumber, Nationality nationality, boolean isNational) {
        this.name = name;
        this.logo = logo;
        this.taxNumber = taxNumber;
        this.nationality = nationality;
        this.isNational = isNational;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    public boolean isNational() {
        return isNational;
    }

    public void setNational(boolean national) {
        isNational = national;
    }
}
