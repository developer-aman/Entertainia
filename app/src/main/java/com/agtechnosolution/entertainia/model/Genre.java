package com.agtechnosolution.entertainia.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by AnujPc on 05-12-2018.
 */

public class Genre {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String genreName;

    @SerializedName("genres")
    public List<Genre> genresBundle;

    public Genre(int id, String genreName) {
        this.id = id;
        this.genreName = genreName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }
}
