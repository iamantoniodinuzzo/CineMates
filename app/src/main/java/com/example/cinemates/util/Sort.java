package com.example.cinemates.util;

import androidx.annotation.NonNull;

/**
 * @author Antonio Di Nuzzo
 * Created 23/07/2022 at 10:51
 */
public enum Sort {
    POPULARITY("popularity.desc"),
    RELEASE_DATE("release_date.desc"),
    VOTE_AVERAGE("vote_average.desc");

    private final String attribute;

    public String getAttribute() {
        return attribute;
    }

    Sort(String attribute) {
        this.attribute = attribute;
    }

    @NonNull
    @Override
    public String toString() {
        switch (this) {
            case POPULARITY:
                return "Most Popular";
            case RELEASE_DATE:
                return "Next Movies";
            case VOTE_AVERAGE:
                return "Most voted";

        }
        throw new AssertionError("Unknown " + this);

    }


}
