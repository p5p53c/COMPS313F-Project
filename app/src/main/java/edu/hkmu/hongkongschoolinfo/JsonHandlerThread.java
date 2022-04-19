package edu.hkmu.hongkongschoolinfo;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;

public class JsonHandlerThread extends Thread {
    private static final String TAG = "JsonHandlerThread";
    // URL to get contacts JSON file
    private static String jsonUrl = "https://www.edb.gov.hk/attachment/en/student-parents/sch-info/sch-search/sch-location-info/SCH_LOC_EDB.json";

    @Override
    public void run() {
        try {
            //parse String to URL
            URL url = new URL(jsonUrl);
            Gson gson = new Gson();

            //get json from URL
            InputStreamReader inputStream = new InputStreamReader(url.openStream());
            Type schoolType = new TypeToken<ArrayList<School>>(){}.getType();
            //store json to arraylist
            ArrayList<School> schools = gson.fromJson(inputStream, schoolType);
            Log.i(TAG, "run: " + schools);
            schools.remove(0);
            //store arraylist to the java bean
            School.allSchoolList = schools;
        } catch (IOException e) {
            Log.e(TAG, "JsonHandlerThread.run: " + e.getMessage());
        }
    }
}