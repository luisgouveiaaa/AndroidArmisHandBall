package com.example.debs.androidarmishandball.restclient.dto;

import java.io.Serializable;

/**
 * Created by Debs on 21/08/17.
 */

public class SearchResult implements Serializable{

    private int pk;
    private SearchableType type;
    private String name;
    private byte[] image;

    public SearchResult(){

    }

    public SearchResult(int pk, int type, String name, byte[] image)
    {
        this.pk = pk;
        this.type = SearchableType.valueOf(String.valueOf(type));
        this.name = name;
        this.image = image;
    }
    public SearchResult(int pk, String name, byte[] image)
    {
        this.pk = pk;
        this.name = name;
        this.image = image;
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public SearchableType getType() {
        return type;
    }

    public void setType(SearchableType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public enum SearchableType {Club, Tournament, Athlete};
}



