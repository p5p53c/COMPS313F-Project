package edu.hkmu.hongkongschoolinfo.RoomDataBase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "MyFavourite")
public class MyFavourite {

    @PrimaryKey(autoGenerate = true)
    private long schoolNo;

    public MyFavourite(long schoolNo) {
        this.schoolNo = schoolNo;
    }

    public long getSchoolNo() {
        return schoolNo;
    }

    public void setSchoolNo(long schoolNo) {
        this.schoolNo = schoolNo;
    }
}
