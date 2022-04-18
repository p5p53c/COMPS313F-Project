package edu.hkmu.hongkongschoolinfo;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);

        // Create an adapter object that accommodates a data list of items to views that becomes children of an adapter view
        // i.e. the Adapter object acts as a bridge between an ListView and the contacts for that view
        JsonHandlerThread jsonHandlerThread = new JsonHandlerThread();
        jsonHandlerThread.start();
        try {
            jsonHandlerThread.join();

            //get the java bean
            SimpleAdapter adapter = new SimpleAdapter(
                    this,
                    SchoolList.schoolList,
                    R.layout.list_view_layout,
                    new String[] { SchoolList.SCHOOLNO, SchoolList.NAME, SchoolList.CATEGORY,
                            SchoolList.ADDRESS, SchoolList.LONGITUDE, SchoolList.LATITUDE,
                    SchoolList.EASTING, SchoolList.NORTHING, SchoolList.GENDER, SchoolList.SESSION,
                    SchoolList.DISTRICT, SchoolList.FINANCE, SchoolList.LEVEL, SchoolList.PHONE,
                    SchoolList.FAX, SchoolList.WEBSITE, SchoolList.RELIGION},
                    new int[] { R.id.schoolNo, R.id.name, R.id.category, R.id.address, R.id.longitude,
                    R.id.latitude, R.id.easting, R.id.northing, R.id.gender, R.id.session, R.id.district,
                    R.id.finance, R.id.level, R.id.phone, R.id.fax, R.id.website, R.id.religion}
            );
            // Associate the adapter with the ListView
            listView.setAdapter(adapter);

            // Create an adapter object that accommodates a data list of items to views that becomes children of an adapter view
            // i.e. the Adapter object acts as a bridge between an ListView and the contacts for that view
        } catch (InterruptedException e) {
            Log.e(TAG, "InterruptedException: " + e.getMessage());
        }
    }
}