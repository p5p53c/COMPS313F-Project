package edu.hkmu.hongkongschoolinfo.RoomDataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {MyFavourite.class}, version = 1)
public abstract class DataBase extends RoomDatabase {
    public static final String DB_NAME = "MyFavourite.db";
    private static volatile DataBase instance;

    public static synchronized DataBase getInstance(Context context) {
        if (instance == null)
            instance = create(context);
        return instance;
    }

    private static DataBase create(final Context context) {
        return Room.databaseBuilder(context, DataBase.class, DB_NAME).build();
    }

    public abstract DataUao getDataUao();

}
