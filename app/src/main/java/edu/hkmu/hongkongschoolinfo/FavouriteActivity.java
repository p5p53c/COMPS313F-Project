package edu.hkmu.hongkongschoolinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import edu.hkmu.hongkongschoolinfo.RoomDataBase.DataBase;
import edu.hkmu.hongkongschoolinfo.RoomDataBase.MyFavourite;

public class FavouriteActivity extends AppCompatActivity {

    private ListView listViewFavourite;
    private TextView favouriteMissing;
    private long[] favouriteSchoolNo;
    private ArrayList<School> favouriteSchool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFavouriteSchoolNo();
        setContentView(R.layout.activity_favourite);

        listViewFavourite = findViewById(R.id.listviewFavourite);
        favouriteMissing = findViewById(R.id.favourite_missing);
        setUpOnclickListener();
    }

    private void setListViewFavourite() {
        if (favouriteSchool != null) {
            SchoolAdapter adapter = new SchoolAdapter(getApplicationContext(), 0, favouriteSchool);
            listViewFavourite.setAdapter(adapter);
        } else {
            listViewFavourite.setVisibility(View.GONE);
            favouriteMissing.setVisibility(View.VISIBLE);
        }
    }

    private void getFavouriteSchoolNo() {
        new Thread(() -> {
            List<MyFavourite> favourites = DataBase.getInstance(this).getDataUao().displayAll();
            if (favourites.size() > 0) {
                favouriteSchoolNo = new long[favourites.size()];
                for (int i = 0; i < favourites.size(); i++) {
                    favouriteSchoolNo[i] = favourites.get(i).getSchoolNo();
                }
            } else
                favouriteSchoolNo = new long[0];
            setFavouriteSchoolList();
        }).start();
    }

    private void setFavouriteSchoolList() {
        favouriteSchool = new ArrayList<>();
        if (favouriteSchoolNo.length > 0) {
            for (School school:
                    School.allSchoolList) {
                for (int i = 0; i < favouriteSchoolNo.length; i++) {
                    if (Long.parseLong(school.getSCHOOLNO()) == favouriteSchoolNo[i])
                        favouriteSchool.add(school);
                }
            }
        } else {
            favouriteSchool = null;
        }
        runOnUiThread(() -> {
            setListViewFavourite();
        });
    }

    private void setUpOnclickListener() {
        listViewFavourite.setOnItemClickListener((parent, view, position, id) -> {
            School selectSchool = (School) (listViewFavourite.getItemAtPosition(position));
            Intent schoolContact = new Intent(getApplicationContext(), ContactDialog.class);
            schoolContact.putExtra("phone", selectSchool.getPHONE());
            schoolContact.putExtra("website", selectSchool.getWEBSITE());
            schoolContact.putExtra("schoolno", selectSchool.getSCHOOLNO());
            startActivity(schoolContact);
        });
    }
}