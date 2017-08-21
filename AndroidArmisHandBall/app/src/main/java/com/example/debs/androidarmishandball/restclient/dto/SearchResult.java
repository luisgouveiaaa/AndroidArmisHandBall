package com.example.debs.androidarmishandball.restclient.dto;

/**
 * Created by Debs on 21/08/17.
 */

public class SearchResult {

    public int pk;
    public SearchableType type;
    public String name;
    public byte[] image;

    public SearchResult(int pk, int type, String name, byte[] image)
    {
        this.pk = pk;
        this.type = SearchableType.valueOf(String.valueOf(type));
        this.name = name;
        this.image = image;
    }

    public SearchResult(){

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



