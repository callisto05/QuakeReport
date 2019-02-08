package com.example.android.quakereport;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

public class EarthquakeAdapter extends ArrayAdapter {
    public EarthquakeAdapter(Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Earthquake currentEarthquake = (Earthquake) getItem(position);

        TextView magnitude = listItemView.findViewById(R.id.magnitude);
        TextView location = listItemView.findViewById(R.id.location);
        TextView date = listItemView.findViewById(R.id.date);

        magnitude.setText(String.valueOf(currentEarthquake.getMagnitude()));
        location.setText(currentEarthquake.getLocation());
        date.setText(currentEarthquake.getDate());



        return listItemView;
    }
}
