package com.example.android.quakereport;

import java.util.ArrayList;

public class Earthquake {
    private double mMagnitude;
    private String mLocation;
    private  String mDate;

    public Earthquake(double mMagnitude, String mLocation, String mDate) {
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

    public String getDate() {
        return mDate;
    }
}

