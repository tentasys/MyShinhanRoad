package com.example.shinple.vo;


import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.Log;

import com.example.shinple.BackgroundTask;
import com.example.shinple.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;

public class Painting {

    private final int imageId;
    private final String title;
    private final String year;
    private final String location;

    private Painting(int imageId, String title, String year, String location) {
        this.imageId = imageId;
        this.title = title;
        this.year = year;
        this.location = location;
    }

    public int getImageId() {
        return imageId;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getLocation() {
        return location;
    }

    public static Painting[] getAllPaintings(Resources res) {

        String result_painting = "";
        Painting[] paintings;
        BackgroundTask backgroundTask = new BackgroundTask("app/cop.php");
        TypedArray images = res.obtainTypedArray(R.array.paintings_images);
        try{
            result_painting = backgroundTask.execute().get();
            JSONObject jsonObject = new JSONObject(result_painting);
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            String cop_num, cop_point, cop_intro, cop_chief;
            int count = 0;
            paintings = new Painting[jsonArray.length()];
            while(count < jsonArray.length()){
                JSONObject object = jsonArray.getJSONObject(count);

                cop_num = object.getString("cop_num");
                cop_point = object.getString("cop_point");
                cop_point = object.getString("cop_point");

                if(cop_point == null) {
                    cop_point= "0";
                }
                cop_intro = object.getString("cop_intro");
                if(cop_intro == null) {
                    cop_intro= "0";
                }
                cop_chief = object.getString("cop_chief");
                if(cop_chief == null) {
                    cop_chief= "0";
                }
                paintings[count] = new Painting(Integer.parseInt(cop_num), cop_point, cop_intro, cop_chief);
                count++;
            }
            images.recycle();
            return paintings;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
/*
        String[] titles = res.getStringArray(R.array.paintings_titles);
        String[] years = res.getStringArray(R.array.paintings_years);
        String[] locations = res.getStringArray(R.array.paintings_locations);
        TypedArray images = res.obtainTypedArray(R.array.paintings_images);

        int size = titles.length;
        Painting[] paintings = new Painting[size];

        for (int i = 0; i < size; i++) {
            final int imageId = images.getResourceId(i, -1);
            paintings[i] = new Painting(imageId, titles[i], years[i], locations[i]);
        }

        images.recycle();

        return paintings;
    }*/

