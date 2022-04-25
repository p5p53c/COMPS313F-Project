package edu.hkmu.hongkongschoolinfo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.stetho.Stetho;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";
    private EditText search_bar;
    private Spinner staticSpinner, dynamicSpinner;
    private Button filterButton;
    private ListView listView;
    private String[] filter = new String[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);

        search_bar = findViewById(R.id.search_bar);
        staticSpinner = findViewById(R.id.staticSpinner);
        dynamicSpinner = findViewById(R.id.dynamicSpinner);
        filterButton = findViewById(R.id.filterButton);
        listView = findViewById(R.id.listview);

        // Create an adapter object that accommodates a data list of items to views that becomes children of an adapter view
        // i.e. the Adapter object acts as a bridge between an ListView and the contacts for that view
        JsonHandlerThread jsonHandlerThread = new JsonHandlerThread();
        jsonHandlerThread.start();

        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.filter,
                        android.R.layout.simple_spinner_item);
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        staticSpinner.setAdapter(staticAdapter);

        try {
            jsonHandlerThread.join();

            setUpList();
            setUpOnclickListener();
            filterButtonOnclickListener();

            // Create an adapter object that accommodates a data list of items to views that becomes children of an adapter view
            // i.e. the Adapter object acts as a bridge between an ListView and the contacts for that view
        } catch (InterruptedException e) {
            Log.e(TAG, "InterruptedException: " + e.getMessage());
        }

        staticSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("Static Spinner", "Position : " + position);
                filter[0] = position + "";
                ArrayAdapter<CharSequence> dynamicAdapter;
                switch (position) {
                    case 0:
                        dynamicSpinner.setAdapter(null);
                        filter[1] = "";
                        break;
                    case 1:
                        dynamicAdapter = ArrayAdapter
                                .createFromResource(MainActivity.this, R.array.categoryfilter,
                                        android.R.layout.simple_spinner_item);
                        dynamicAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        dynamicSpinner.setAdapter(dynamicAdapter);
                        break;
                    case 2:
                        dynamicAdapter = ArrayAdapter
                                .createFromResource(MainActivity.this, R.array.genderfilter,
                                        android.R.layout.simple_spinner_item);
                        dynamicAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        dynamicSpinner.setAdapter(dynamicAdapter);
                        break;
                    case 3:
                        dynamicAdapter = ArrayAdapter
                                .createFromResource(MainActivity.this, R.array.sessionfilter,
                                        android.R.layout.simple_spinner_item);
                        dynamicAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        dynamicSpinner.setAdapter(dynamicAdapter);
                        break;
                    case 4:
                        dynamicAdapter = ArrayAdapter
                                .createFromResource(MainActivity.this, R.array.districtfilter,
                                        android.R.layout.simple_spinner_item);
                        dynamicAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        dynamicSpinner.setAdapter(dynamicAdapter);
                        break;

                    case 5:
                        dynamicAdapter = ArrayAdapter
                                .createFromResource(MainActivity.this, R.array.financefilter,
                                        android.R.layout.simple_spinner_item);
                        dynamicAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        dynamicSpinner.setAdapter(dynamicAdapter);
                        break;

                    case 6:
                        dynamicAdapter = ArrayAdapter
                                .createFromResource(MainActivity.this, R.array.levelfilter,
                                        android.R.layout.simple_spinner_item);
                        dynamicAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        dynamicSpinner.setAdapter(dynamicAdapter);
                        break;
                    case 7:
                        dynamicAdapter = ArrayAdapter
                                .createFromResource(MainActivity.this, R.array.religionfilter,
                                        android.R.layout.simple_spinner_item);
                        dynamicAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        dynamicSpinner.setAdapter(dynamicAdapter);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dynamicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("Dynamic Spinner", "Position : " + position + ", " + parent.getItemAtPosition(position));
                filter[1] = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setUpList() {
        listView = findViewById(R.id.listview);

        SchoolAdapter adapter = new SchoolAdapter(getApplicationContext(), 0 , School.allSchoolList);
        listView.setAdapter(adapter);
    }

    private ArrayList<School> searchWidget(ArrayList<School> searchSchool) {
        String searchText = search_bar.getText().toString();
        if (searchText.length() > 0) {
            for (School school :
                    School.allSchoolList) {
                if (school.getNAME().toLowerCase(Locale.ROOT).contains(searchText.toLowerCase())) {
                    searchSchool.add(school);
                }
            }
        } else {
            searchSchool.addAll(School.allSchoolList);
        }
        return searchSchool;
    }

    private ArrayList<School> schoolFilter(ArrayList<School> filterSchool) {
        ArrayList<School> filterList = new ArrayList<>();
        switch (Integer.parseInt(filter[0])) {
            case 1:
                for (School school :
                        filterSchool) {
                    if (school.getCATEGORY().contains(filter[1])) {
                        filterList.add(school);
                    }
                }
                break;
            case 2:
                for (School school :
                        filterSchool) {
                    if (school.getGENDER().contains(filter[1])) {
                        filterList.add(school);
                    }
                }
                break;
            case 3:
                for (School school :
                        filterSchool) {
                    if (school.getSESSION().contains(filter[1])) {
                        filterList.add(school);
                    }
                }
                break;
            case 4:
                for (School school :
                        filterSchool) {
                    if (school.getDISTRICT().contains(filter[1])) {
                        filterList.add(school);
                    }
                }
                break;
            case 5:
                for (School school :
                        filterSchool) {
                    if (school.getFINANCE().contains(filter[1])) {
                        filterList.add(school);
                    }
                }
                break;
            case 6:
                for (School school :
                        filterSchool) {
                    if (school.getLEVEL().contains(filter[1])) {
                        filterList.add(school);
                    }
                }
                break;
            case 7:
                for (School school :
                        filterSchool) {
                    if (school.getRELIGION().contains(filter[1])) {
                        filterList.add(school);
                    }
                }
                break;
            default:
                return filterSchool;
        }
        return filterList;
    }

    private void setUpOnclickListener() {
        listView.setOnItemClickListener((parent, view, position, id) -> {
            School selectSchool = (School) (listView.getItemAtPosition(position));
            Intent schoolContact = new Intent(getApplicationContext(), ContactDialog.class);
            schoolContact.putExtra("phone", selectSchool.getPHONE());
            schoolContact.putExtra("website", selectSchool.getWEBSITE());
            schoolContact.putExtra("schoolno", selectSchool.getSCHOOLNO());
            startActivity(schoolContact);
        });
    }

    private void filterButtonOnclickListener() {
        filterButton.setOnClickListener(v -> {
            Log.v("Search Function", "Search Bar : " + search_bar.getText() + ", Filter : [" + filter[0] + ", " + filter[1] + "]");
            ArrayList<School> filterList = new ArrayList<>();
            filterList = searchWidget(filterList);
            filterList = schoolFilter(filterList);
            SchoolAdapter adapter = new SchoolAdapter(getApplicationContext(), 0, filterList);
            listView.setAdapter(adapter);
        });
    }

    public boolean onOptionsItemSelected(MenuItem item){
        return (applyMenuOption(item) ||
                super.onOptionsItemSelected(item));
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private boolean applyMenuOption(MenuItem item) {
        int menuItemId = item.getItemId();
        if (menuItemId == R.id.menuExit) {
            finish();
        } else if (menuItemId == R.id.menuFavourite) {
            Intent intent = new Intent(this, FavouriteActivity.class);
            startActivity(intent);
        }
        return false;
    }
}