package com.g2mdx.eltager.model.dto;

import androidx.room.Entity;

@Entity(tableName = "farm")
public class Farm  {

    private int id;
    private String name;
    private long createdAt;
    private String season;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }
}
