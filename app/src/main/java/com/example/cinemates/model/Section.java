package com.example.cinemates.model;

import java.util.List;

/**
 * @author Antonio Di Nuzzo
 * Created 15/12/2021 at 20:54
 */
public class Section {

    private String sectionName;
    private List<Movie> sectionItems;

    public Section(String sectionName, List<Movie> sectionItems) {
        this.sectionName = sectionName;
        this.sectionItems = sectionItems;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public List<Movie> getSectionItems() {
        return sectionItems;
    }

    public void setSectionItems(List<Movie> sectionItems) {
        this.sectionItems = sectionItems;
    }
}
