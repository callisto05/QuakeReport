/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Earthquake>> {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=6&limit=10";
    private EarthquakeAdapter mAdapter;
//    Textview that is displayed when the listview is empty
    private TextView emptyText;
    ProgressBar progressBar;

    public static String getU(){
        return USGS_REQUEST_URL;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        ProgressBar progressBar = findViewById(R.id.progress);


        emptyText = findViewById(R.id.emptyView);

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = (activeNetwork != null) && activeNetwork.isConnected();

//        new EarthquakeAsyncTask().execute(USGS_REQUEST_URL);

        Log.i("LOG_TAG", "Loader Initialized_______");

        if (isConnected == true) {
//       Initialize the Loader and start execution
            getSupportLoaderManager().initLoader(1, null, this);
        }

        else {
            progressBar = findViewById(R.id.progress);
            progressBar.setVisibility(View.GONE);
            emptyText.setText("No Internet Connection");
        }



        mAdapter = new EarthquakeAdapter(EarthquakeActivity.this, new ArrayList<Earthquake>());
        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);
        earthquakeListView.setAdapter(mAdapter);

//      Setting EmptyView
        earthquakeListView.setEmptyView(emptyText);



        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Earthquake earthquake = (Earthquake) mAdapter.getItem(position);

                Intent browserInt = new Intent(Intent.ACTION_VIEW);
                browserInt.setData(Uri.parse(earthquake.getUrl()));

                startActivity(browserInt);

            }
        });
    }


//      Create object of the loader and return the List of earthquakes
    @NonNull
    @Override
    public Loader<List<Earthquake>> onCreateLoader(int i, @Nullable Bundle bundle) {
        Log.v("LOG_TAG", "onCreateLoader called______");
        return new EarthquakeLoader(EarthquakeActivity.this);
    }

//    Set the loader data to UI adapter
    @Override
    public void onLoadFinished(@NonNull Loader<List<Earthquake>> loader, List<Earthquake> earthquakes) {
        progressBar = findViewById(R.id.progress);
        progressBar.setVisibility(View.GONE);
        mAdapter.clear();
        Log.v("LOG_TAG", "onLoadFinished called_______");
        if(earthquakes != null && !earthquakes.isEmpty()) {
            //mAdapter.addAll(earthquakes);

        }
//      Setting the text in emptyview if the listview is null
        emptyText.setText(R.string.no_earthquake);

    }
//    Clear the Loader Data
    @Override
    public void onLoaderReset(@NonNull Loader<List<Earthquake>> loader) {
        Log.v("LOG_TAG", "onLoaderReset called_______");
        mAdapter.addAll(new ArrayList<Earthquake>());
    }


//    public class EarthquakeAsyncTask extends AsyncTask<String, Void, List<Earthquake>> {
//        List<Earthquake> earthquakeList;
//
//        @Override
//        protected List<Earthquake> doInBackground(String... strings) {
//            earthquakeList = QueryUtils.fetchEarthquakeData(strings[0]);
//            return earthquakeList;
//        }
//
//
//        @Override
//        protected void onPostExecute(List<Earthquake> earthquakes) {
//            super.onPostExecute(earthquakes);
//            mAdapter.clear();
//
//            if(earthquakes != null && !earthquakes.isEmpty()) {
//                mAdapter.addAll(earthquakes);
//            }
//
//        }
//    }


}
