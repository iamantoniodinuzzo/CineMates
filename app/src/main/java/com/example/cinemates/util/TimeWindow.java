package com.example.cinemates.util;

/**
 * @author Antonio Di Nuzzo
 * Created 28/05/2022 at 10:36
 */
public enum TimeWindow {
    WEEK("week"), DAY("day");
    private final String value;

    TimeWindow(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
