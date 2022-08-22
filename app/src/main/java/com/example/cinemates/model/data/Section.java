package com.example.cinemates.model.data;

import androidx.lifecycle.MutableLiveData;

import com.example.cinemates.util.ViewSize;

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
    private String sectionContentDescription;
    private MutableLiveData<List<T>> mMutableLiveData;
    private ViewSize mViewSize;


    public Section(String sectionName, String sectionContentDescription, Class<T> c, MutableLiveData<List<T>> mutableLiveData, ViewSize viewSize) {
        this.sectionName = sectionName;
        mMutableLiveData = mutableLiveData;
        this.genericType = c;
        this.sectionContentDescription = sectionContentDescription;
        this.mViewSize = viewSize;
    }

    public Class<T> getGenericType() {
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

    public String getSectionContentDescription() {
        return sectionContentDescription;
    }

    public void setSectionContentDescription(String sectionContentDescription) {
        this.sectionContentDescription = sectionContentDescription;
    }

    public ViewSize getViewSize() {
        return mViewSize;
    }

    public void setViewSize(ViewSize viewSize) {
        mViewSize = viewSize;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Section)) return false;
        if (!super.equals(o)) return false;
        Section<?> section = (Section<?>) o;
        return getGenericType().equals(section.getGenericType()) && getSectionName().equals(section.getSectionName()) && Objects.equals(getSectionContentDescription(), section.getSectionContentDescription()) && Objects.equals(getMutableLiveData(), section.getMutableLiveData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getGenericType(), getSectionName(), getSectionContentDescription(), getMutableLiveData());
    }
}
