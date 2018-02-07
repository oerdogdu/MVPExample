package com.example.atoi.mvptrial.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Atoi on 4.01.2018.
 */

public class News {

    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    public News(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return title;
    }

    public void setText(String title) {
        this.title = title;
    }
}
