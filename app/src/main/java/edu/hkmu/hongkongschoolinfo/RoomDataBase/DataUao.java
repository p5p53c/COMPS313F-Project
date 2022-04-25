package edu.hkmu.hongkongschoolinfo.RoomDataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DataUao {

    String tableName = "MyFavourite";

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertData(MyFavourite myFavourite);

    @Query("INSERT INTO " + tableName + "(schoolNo) VALUES(:schoolNo)")
    void insertData(int schoolNo);

    @Query("SELECT * FROM " + tableName)
    List<MyFavourite> displayAll();

    @Query("SELECT * FROM " + tableName + " WHERE schoolNo = :schoolNo")
    List<MyFavourite> findDataBySchoolNo(long schoolNo);

    @Delete
    void deleteData(MyFavourite myFavourite);

    @Query("DELETE FROM " + tableName + " WHERE schoolNo = :schoolNo")
    void deleteData(long schoolNo);
}
