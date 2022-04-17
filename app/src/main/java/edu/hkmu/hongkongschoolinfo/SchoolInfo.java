package edu.hkmu.hongkongschoolinfo;

import com.google.gson.annotations.SerializedName;

// A utility class for creating contact list
public class SchoolInfo {
    @SerializedName("A")
    private String SCHOOLNO;
    @SerializedName("B")
    private String CATEGORY;
    @SerializedName("D")
    private String NAME;
    @SerializedName("F")
    private String ADDRESS;
    @SerializedName("H")
    private String LONGITUDE;
    @SerializedName("J")
    private String LATITUDE;
    @SerializedName("L")
    private String EASTING;
    @SerializedName("N")
    private String NORTHING;
    @SerializedName("P")
    private String GENDER;
    @SerializedName("R")
    private String SESSION;
    @SerializedName("T")
    private String DISTRICT;
    @SerializedName("V")
    private String FINANCE;
    @SerializedName("X")
    private String LEVEL;
    @SerializedName("Z")
    private String PHONE;
    @SerializedName("AB")
    private String FAX;
    @SerializedName("AD")
    private String WEBSITE;
    @SerializedName("AF")
    private String RELIGION;

    public SchoolInfo(String SCHOOLNO, String CATEGORY, String NAME, String ADDRESS, String LONGITUDE, String LATITUDE, String EASTING, String NORTHING, String GENDER, String SESSION, String DISTRICT, String FINANCE, String LEVEL, String PHONE, String FAX, String WEBSITE, String RELIGION) {
        this.SCHOOLNO = SCHOOLNO;
        this.CATEGORY = CATEGORY;
        this.NAME = NAME;
        this.ADDRESS = ADDRESS;
        this.LONGITUDE = LONGITUDE;
        this.LATITUDE = LATITUDE;
        this.EASTING = EASTING;
        this.NORTHING = NORTHING;
        this.GENDER = GENDER;
        this.SESSION = SESSION;
        this.DISTRICT = DISTRICT;
        this.FINANCE = FINANCE;
        this.LEVEL = LEVEL;
        this.PHONE = PHONE;
        this.FAX = FAX;
        this.WEBSITE = WEBSITE;
        this.RELIGION = RELIGION;
    }

    public String getSCHOOLNO() {
        return SCHOOLNO;
    }

    public String getCATEGORY() {
        return CATEGORY;
    }

    public String getNAME() {
        return NAME;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public String getLONGITUDE() {
        return LONGITUDE;
    }

    public String getLATITUDE() {
        return LATITUDE;
    }

    public String getEASTING() {
        return EASTING;
    }

    public String getNORTHING() {
        return NORTHING;
    }

    public String getGENDER() {
        return GENDER;
    }

    public String getSESSION() {
        return SESSION;
    }

    public String getDISTRICT() {
        return DISTRICT;
    }

    public String getFINANCE() {
        return FINANCE;
    }

    public String getLEVEL() {
        return LEVEL;
    }

    public String getPHONE() {
        return PHONE;
    }

    public String getFAX() {
        return FAX;
    }

    public String getWEBSITE() {
        return WEBSITE;
    }

    public String getRELIGION() {
        return RELIGION;
    }
}
