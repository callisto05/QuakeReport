package com.example.android.quakereport;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeoutException;

public class EarthquakeAdapter extends ArrayAdapter {
    public EarthquakeAdapter(Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Earthquake currentEarthquake = (Earthquake) getItem(position);

        TextView magnitude = listItemView.findViewById(R.id.magnitude);
        TextView locationOffset = listItemView.findViewById(R.id.locationOffset);
        TextView Primarylocation = listItemView.findViewById(R.id.PrimaryLoc);
        TextView date = listItemView.findViewById(R.id.date);
        TextView time = listItemView.findViewById(R.id.time);

        DecimalFormat decimalFormat = new DecimalFormat("0.0");

        String formattedMagnitude = decimalFormat.format(currentEarthquake.getMagnitude());

        magnitude.setText(formattedMagnitude);

        GradientDrawable magnitude_circle = (GradientDrawable) magnitude.getBackground();
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());
        magnitude_circle.setColor(magnitudeColor);



        Date dateObj = new Date(currentEarthquake.getDate());

        String formattedDate = formatDate(dateObj);
        String formattedTime = formatTime(dateObj);

        date.setText(formattedDate);
        time.setText(formattedTime);
        //Get raw location string and store in a variable for operations
        String locationString = currentEarthquake.getLocation();
        String primaryString = getPrimaryLocation(locationString);
        String locOffset;

        //if string has "of" set it to the offset view else set "Near the"

        if (locationString.contains("of")) {
            locOffset = getLocationOffset(locationString);
        } else {
            locOffset = "Near the";
        }

        locationOffset.setText(locOffset);
        Primarylocation.setText(primaryString);


        return listItemView;
    }


    //get location offset by cutting string till "of"
    private String getLocationOffset(String location) {
        return location.substring(0, location.indexOf("of") + 2);
    }


    // get primary location by cutting string from "of "
    private String getPrimaryLocation(String location) {
        return location.substring(location.indexOf("of") + 3);
    }


    private String formatDate(Date date) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyy");
        return dateFormatter.format(date);
    }

    private String formatTime(Date time) {
        SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mm a");
        return timeFormatter.format(time);
    }

    private int getMagnitudeColor(Double magnitude){
        int colorId;
        int magnitudeFloor =(int) Math.floor(magnitude);
        switch (magnitudeFloor){
            case 0:
            case 1:
                colorId = R.color.magnitude1;
                break;

            case 2:
                colorId = R.color.magnitude2;
                break;

            case 3:
                colorId = R.color.magnitude3;
                break;

            case 4:
                colorId = R.color.magnitude4;
                break;


            case 5:
                colorId = R.color.magnitude5;
                break;


            case 6:
                colorId = R.color.magnitude6;
                break;

            case 7:
                colorId = R.color.magnitude7;
                break;

            case 8:
                colorId = R.color.magnitude8;
                break;

            case 9:
                colorId = R.color.magnitude9;
                break;

            default:
                colorId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), colorId);
    }
}