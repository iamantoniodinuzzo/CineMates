package com.example.cinemates.model;

/**
 * @author Antonio Di Nuzzo
 * Created 26/05/2022 at 19:14
 */
public class ProductionCompany {
    private String name, logo_path, origin_country;
    private Integer id;

    private ProductionCompany(String name, String logo_path, String origin_country, Integer id) {
        this.name = name;
        this.logo_path = logo_path;
        this.origin_country = origin_country;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo_path() {
        return logo_path;
    }

    public void setLogo_path(String logo_path) {
        this.logo_path = logo_path;
    }

    public String getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(String origin_country) {
        this.origin_country = origin_country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }
}
