package edu.hkmu.hongkongschoolinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ContactDialog extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "URL_MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_contact);

        Intent intent = getIntent();

        Button dialog_phone = findViewById(R.id.dialog_phone);
        Button dialog_website = findViewById(R.id.dialog_website);

        int schoolPhoneNumber = 0;

        try {
            schoolPhoneNumber = Integer.parseInt(intent.getStringExtra("phone"));
        } catch (Exception e) {
            dialog_phone.setVisibility(View.GONE);

        }

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
}