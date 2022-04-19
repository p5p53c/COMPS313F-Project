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
            //Log.i(TAG, "run: " + schoolInfos.get(1));

            for (int i = 1; i < School.allSchoolList.size(); i++) {
                String id = School.allSchoolList.get(i).getSCHOOLNO();
                String category = School.allSchoolList.get(i).getCATEGORY();
                String name = School.allSchoolList.get(i).getNAME();
                String address = School.allSchoolList.get(i).getADDRESS();
                String longitude = School.allSchoolList.get(i).getLONGITUDE();
                String latitude = School.allSchoolList.get(i).getLATITUDE();
                String easting = School.allSchoolList.get(i).getEASTING();
                String northing = School.allSchoolList.get(i).getNORTHING();
                String gender = School.allSchoolList.get(i).getGENDER();
                String session = School.allSchoolList.get(i).getSESSION();
                String district = School.allSchoolList.get(i).getDISTRICT();
                String finance = School.allSchoolList.get(i).getFINANCE();
                String level = School.allSchoolList.get(i).getLEVEL();
                String phone = School.allSchoolList.get(i).getPHONE();
                String fax = School.allSchoolList.get(i).getFAX();
                String website = School.allSchoolList.get(i).getWEBSITE();
                String religion = School.allSchoolList.get(i).getRELIGION();

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