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
            Type schoolType = new TypeToken<ArrayList<SchoolInfo>>(){}.getType();
            //store json to arraylist
            ArrayList<SchoolInfo> schools = gson.fromJson(inputStream, schoolType);
            Log.i(TAG, "run: " + schools);
            //store arraylist to the java bean
            SchoolInfo.schoolList = schools;
            //Log.i(TAG, "run: " + schoolInfos.get(1));

            for (int i = 1; i < SchoolInfo.schoolList.size(); i++) {
                String id = SchoolInfo.schoolList.get(i).getSCHOOLNO();
                String category = SchoolInfo.schoolList.get(i).getCATEGORY();
                String name = SchoolInfo.schoolList.get(i).getNAME();
                String address = SchoolInfo.schoolList.get(i).getADDRESS();
                String longitude = SchoolInfo.schoolList.get(i).getLONGITUDE();
                String latitude = SchoolInfo.schoolList.get(i).getLATITUDE();
                String easting = SchoolInfo.schoolList.get(i).getEASTING();
                String northing = SchoolInfo.schoolList.get(i).getNORTHING();
                String gender = SchoolInfo.schoolList.get(i).getGENDER();
                String session = SchoolInfo.schoolList.get(i).getSESSION();
                String district = SchoolInfo.schoolList.get(i).getDISTRICT();
                String finance = SchoolInfo.schoolList.get(i).getFINANCE();
                String level = SchoolInfo.schoolList.get(i).getLEVEL();
                String phone = SchoolInfo.schoolList.get(i).getPHONE();
                String fax = SchoolInfo.schoolList.get(i).getFAX();
                String website = SchoolInfo.schoolList.get(i).getWEBSITE();
                String religion = SchoolInfo.schoolList.get(i).getRELIGION();

                SchoolList.addSchool(id, category, name, address,
                        longitude, latitude, easting, northing, gender,
                        session, district, finance, level, phone, fax,
                        website, religion);
            }
        } catch (IOException e) {
            Log.e(TAG, "JsonHandlerThread.run: " + e.getMessage());
        }
    }
}