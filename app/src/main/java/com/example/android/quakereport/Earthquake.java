package com.example.android.quakereport;

import java.util.ArrayList;

public class Earthquake {
    private double mMagnitude;
    private String mLocation;
    private  Long mDate;
    private String mUrl;

    public Earthquake(double mMagnitude, String mLocation, Long mDate,String url) {
        this.mMagnitude = mMagnitude;
        this.mLocation = mLocation;
        this.mDate = mDate;
        this.mUrl = url;
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

    public String getUrl() {
        return mUrl;
    }
}

