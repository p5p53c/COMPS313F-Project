package edu.hkmu.hongkongschoolinfo;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";
    private SearchView search_bar;
    private Spinner staticSpinner, dynamicSpinner;
    private ListView listView;
    private Dialog contentDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search_bar = findViewById(R.id.search_bar);
        staticSpinner = findViewById(R.id.staticSpinner);
        dynamicSpinner = findViewById(R.id.dynamicSpinner);
        listView = findViewById(R.id.listview);

        String[] filer = new String[2];

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
            searchWidget();
            setUpOnclickListener();

            // Create an adapter object that accommodates a data list of items to views that becomes children of an adapter view
            // i.e. the Adapter object acts as a bridge between an ListView and the contacts for that view
        } catch (InterruptedException e) {
            Log.e(TAG, "InterruptedException: " + e.getMessage());
        }

        staticSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("Static Spinner", "Position : " + position);
                filer[0] = position + "";
                ArrayAdapter<CharSequence> dynamicAdapter;
                switch (position) {
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
                Log.v("Dynamic Spinner", "Position : " + position + ", " + (String) parent.getItemAtPosition(position));
                filer[1] = (String) parent.getItemAtPosition(position);
                filterSchool(filer);
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

    private void searchWidget() {

        search_bar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String searchText) {
                if (searchText.length() <= 0) {
                    ArrayList<School> searchSchool = new ArrayList<>();
                    for (School school :
                            School.allSchoolList) {
                        if (school.getNAME().toLowerCase(Locale.ROOT).contains(searchText.toLowerCase())) {
                            searchSchool.add(school);
                        }

                    }
                    SchoolAdapter adapter = new SchoolAdapter(getApplicationContext(), 0, searchSchool);
                    listView.setAdapter(adapter);
                } else
                    setUpList();
                return false;
            }
        });
    }

    private void filterSchool(String[] filter) {
        ArrayList<School> filterSchool = new ArrayList<>();
        switch (Integer.parseInt(filter[0])) {
            case 1:
                for (School school :
                        School.allSchoolList) {
                    if (school.getCATEGORY().contains(filter[1])) {
                        filterSchool.add(school);
                    }
                }
                break;
            case 2:
                for (School school :
                        School.allSchoolList) {
                    if (school.getGENDER().contains(filter[1])) {
                        filterSchool.add(school);
                    }
                }
                break;
            case 3:
                for (School school :
                        School.allSchoolList) {
                    if (school.getSESSION().contains(filter[1])) {
                        filterSchool.add(school);
                    }
                }
                break;
            case 4:
                for (School school :
                        School.allSchoolList) {
                    if (school.getDISTRICT().contains(filter[1])) {
                        filterSchool.add(school);
                    }
                }
                break;
            case 5:
                for (School school :
                        School.allSchoolList) {
                    if (school.getFINANCE().contains(filter[1])) {
                        filterSchool.add(school);
                    }
                }
                break;
            case 6:
                for (School school :
                        School.allSchoolList) {
                    if (school.getLEVEL().contains(filter[1])) {
                        filterSchool.add(school);
                    }
                }
                break;
            case 7:
                for (School school :
                        School.allSchoolList) {
                    if (school.getRELIGION().contains(filter[1])) {
                        filterSchool.add(school);
                    }
                }
                break;
            default:
                setUpList();
                break;
        }
        SchoolAdapter adapter = new SchoolAdapter(getApplicationContext(), 0, filterSchool);
        listView.setAdapter(adapter);
    }

    private void setUpOnclickListener() {
        listView.setOnItemClickListener((parent, view, position, id) -> {
            School selectSchool = (School) (listView.getItemAtPosition(position));
            Intent schoolContact = new Intent(getApplicationContext(), ContactDialog.class);
            schoolContact.putExtra("phone", selectSchool.getPHONE());
            schoolContact.putExtra("website", selectSchool.getWEBSITE());
            startActivity(schoolContact);
        });
    }
}