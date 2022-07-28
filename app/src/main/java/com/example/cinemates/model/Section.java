package com.example.cinemates.model;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Antonio Di Nuzzo
 * Created 15/12/2021 at 20:54
 */
public class Section<T> extends ArrayList<T> {
    private Class<T> genericType;
    private String sectionName;
    private MutableLiveData<List<T>> mMutableLiveData;

    public Section(String sectionName, Class<T> c, MutableLiveData<List<T>> mutableLiveData) {
        this.sectionName = sectionName;
        mMutableLiveData = mutableLiveData;
        this.genericType = c;

    }

    public Class<T> getGenericType()
    {
        return genericType;
    }
    public String getSectionName() {
        return sectionName;
    }

    public MutableLiveData<List<T>> getMutableLiveData() {
        return mMutableLiveData;
    }

    public void setMutableLiveData(MutableLiveData<List<T>> mutableLiveData) {
        mMutableLiveData = mutableLiveData;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Section)) return false;
        Section<?> section = (Section<?>) o;
        return Objects.equals(getSectionName(), section.getSectionName()) && Objects.equals(getMutableLiveData(), section.getMutableLiveData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSectionName(), getMutableLiveData());
    }


}
