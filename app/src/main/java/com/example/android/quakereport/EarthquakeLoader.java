package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {
    public EarthquakeLoader(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public List<Earthquake> loadInBackground() {
        List<Earthquake> earthquakesListLoader = new ArrayList<>();
        earthquakesListLoader = QueryUtils.fetchEarthquakeData(EarthquakeActivity.getU());
        return earthquakesListLoader;
    }
}
