package com.example.android.quakereport;

import java.util.ArrayList;

public class Earthquake {
    private double mMagnitude;
    private String mLocation;
    private  Long mDate;

    public Earthquake(double mMagnitude, String mLocation, Long mDate) {
        this.mMagnitude = mMagnitude;
        this.mLocation = mLocation;
        this.mDate = mDate;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public Long getDate() {
        return mDate;
    }
}

