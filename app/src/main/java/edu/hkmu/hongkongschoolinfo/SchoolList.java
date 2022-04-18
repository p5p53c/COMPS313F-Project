package edu.hkmu.hongkongschoolinfo;

import java.util.ArrayList;
import java.util.HashMap;

public class SchoolList {
    public static String SCHOOLNO = "schoolno";
    public static String CATEGORY = "category";
    public static String NAME = "name";
    public static String ADDRESS = "address";
    public static String LONGITUDE = "longitude";
    public static String LATITUDE = "latitude";
    public static String EASTING = "easting";
    public static String NORTHING = "northing";
    public static String GENDER = "gender";
    public static String SESSION = "session";
    public static String DISTRICT = "district";
    public static String FINANCE = "finance";
    public static String LEVEL = "level";
    public static String PHONE = "phone";
    public static String FAX = "fax";
    public static String WEBSITE = "website";
    public static String RELIGION = "religion";



    public static ArrayList<HashMap<String, String>> schoolList = new ArrayList<>();

    // Creates and add contact to contact list
    public static void addSchool(String schoolno, String category, String name,
                                 String address, String longitude,
                                 String latitude, String easting, String northing, String gender,
                                 String session, String district, String finance, String level,
                                 String phone, String fax, String website, String religion) {
        // Create school
        HashMap<String, String> school = new HashMap<>();
        school.put(SCHOOLNO, schoolno);
        school.put(CATEGORY, category);
        school.put(NAME, name);
        school.put(ADDRESS, address);
        school.put(LONGITUDE, longitude);
        school.put(LATITUDE, latitude);
        school.put(EASTING, easting);
        school.put(NORTHING, northing);
        school.put(GENDER, gender);
        school.put(SESSION, session);
        school.put(DISTRICT, district);
        school.put(FINANCE, finance);
        school.put(LEVEL, level);
        school.put(PHONE, phone);
        school.put(FAX, fax);
        school.put(WEBSITE, website);
        school.put(RELIGION, religion);


        // Add school to school list
        schoolList.add(school);
    }
}
