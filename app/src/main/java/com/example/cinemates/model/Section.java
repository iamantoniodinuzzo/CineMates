package com.example.cinemates.model;

import java.util.List;
import java.util.Objects;

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
        sectionItems.clear();
        this.sectionItems = sectionItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Section)) return false;
        Section section = (Section) o;
        return getSectionName().equals(section.getSectionName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSectionName());
    }
}
