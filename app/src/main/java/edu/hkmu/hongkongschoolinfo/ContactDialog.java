package edu.hkmu.hongkongschoolinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import edu.hkmu.hongkongschoolinfo.RoomDataBase.DataBase;
import edu.hkmu.hongkongschoolinfo.RoomDataBase.MyFavourite;

public class ContactDialog extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "URL_MESSAGE";
    private Button dialog_phone, dialog_website, dialog_favourite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_contact);

        Intent intent = getIntent();

        dialog_phone = findViewById(R.id.dialog_phone);
        dialog_website = findViewById(R.id.dialog_website);
        dialog_favourite = findViewById(R.id.dialog_favourite);

        int schoolPhoneNumber = 0;
        long schoolNo = Long.parseLong(intent.getStringExtra("schoolno"));

        try {
            schoolPhoneNumber = Integer.parseInt(intent.getStringExtra("phone").replaceAll("\\s+",""));
        } catch (Exception e) {
            dialog_phone.setVisibility(View.GONE);

        }

        new Thread(() -> {
            List<MyFavourite> favourites = DataBase.getInstance(this).getDataUao().findDataBySchoolNo(schoolNo);
            if (favourites.size() == 0) {
                addtofavourite(schoolNo);
            } else {
                dialog_favourite.setText("Remove from favourite");
                removefromfavourite(schoolNo);
            }
        }).start();


        String schoolWebsite = intent.getStringExtra("website");

        if (!schoolWebsite.contains(".edu.hk"))
            dialog_website.setVisibility(View.GONE);

        if (dialog_phone.getVisibility() == View.GONE && dialog_website.getVisibility() == View.GONE) {
            TextView contact_missing = findViewById(R.id.contact_missing);
            contact_missing.setVisibility(View.VISIBLE);
        }

        int finalSchoolPhoneNumber = schoolPhoneNumber;
        dialog_phone.setOnClickListener(v -> {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + finalSchoolPhoneNumber));
            startActivity(callIntent);
        });

        dialog_website.setOnClickListener(v -> {
            Intent urlIntent = new Intent(ContactDialog.this, WebViewActivity.class);
            urlIntent.putExtra(EXTRA_MESSAGE, schoolWebsite);
            startActivity(urlIntent);
        });

    }

    private void addtofavourite(long schoolNo) {
        dialog_favourite.setOnClickListener(v -> {
            new Thread(() -> {
                MyFavourite favourite = new MyFavourite(schoolNo);
                DataBase.getInstance(this).getDataUao().insertData(favourite);
            }).start();
            finish();
        });
    }

    private void removefromfavourite(long schoolNo) {
        dialog_favourite.setOnClickListener(v -> {
            new Thread(() -> {
                DataBase.getInstance(this).getDataUao().deleteData(schoolNo);
            }).start();
            finish();
        });
    }
}