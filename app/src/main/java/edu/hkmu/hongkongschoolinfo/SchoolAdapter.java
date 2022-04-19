package edu.hkmu.hongkongschoolinfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class SchoolAdapter extends ArrayAdapter<School> {

    public SchoolAdapter(Context context, int resource, List<School> schoolList) {
        super(context, resource, schoolList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        School school = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_layout, parent, false);
        }

        TextView schoolNo, category, name, address, longitude, latitude, easting, northing, gender, session, district, finance, level, phone, website, fax, religion;
        schoolNo = convertView.findViewById(R.id.schoolNo);
        category = convertView.findViewById(R.id.category);
        name = convertView.findViewById(R.id.name);
        address = convertView.findViewById(R.id.address);
        longitude = convertView.findViewById(R.id.longitude);
        latitude = convertView.findViewById(R.id.latitude);
        easting = convertView.findViewById(R.id.easting);
        northing = convertView.findViewById(R.id.northing);
        gender  = convertView.findViewById(R.id.gender);
        session = convertView.findViewById(R.id.session);
        district = convertView.findViewById(R.id.district);
        finance = convertView.findViewById(R.id.finance);
        level = convertView.findViewById(R.id.level);
        phone = convertView.findViewById(R.id.phone);
        fax = convertView.findViewById(R.id.fax);
        website = convertView.findViewById(R.id.website);
        religion = convertView.findViewById(R.id.religion);

        schoolNo.setText(school.getSCHOOLNO());
        category.setText(school.getCATEGORY());
        name.setText(school.getNAME());
        address.setText(school.getADDRESS());
        longitude.setText(school.getLONGITUDE());
        latitude.setText(school.getLATITUDE());
        easting.setText(school.getEASTING());
        northing.setText(school.getNORTHING());
        gender.setText(school.getGENDER());
        session.setText(school.getSESSION());
        district.setText(school.getDISTRICT());
        finance.setText(school.getFINANCE());
        level.setText(school.getLEVEL());
        phone.setText(school.getPHONE());
        fax.setText(school.getFAX());
        website.setText(school.getWEBSITE());
        religion.setText(school.getRELIGION());

        return convertView;
    }
}
