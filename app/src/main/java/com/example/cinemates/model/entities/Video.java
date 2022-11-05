package com.example.cinemates.model.entities;

/**
 * @author Antonio Di Nuzzo
 * Created 21/04/2022 at 15:39
 */
public class Video {
    private String id, key, type, name, site, published_at;
    private boolean official;

    public Video(String id, String key, String type, String name, String site, String published_at, boolean official) {
        this.id = id;
        this.key = key;
        this.type = type;
        this.name = name;
        this.site = site;
        this.published_at = published_at;
        this.official = official;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getPublished_at() {
        return published_at;
    }

    public void setPublished_at(String published_at) {
        this.published_at = published_at;
    }

    public boolean isOfficial() {
        return official;
    }

    public void setOfficial(boolean official) {
        this.official = official;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id='" + id + '\'' +
                ", key='" + key + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", site='" + site + '\'' +
                ", published_at='" + published_at + '\'' +
                ", official=" + official +
                '}';
    }
}
