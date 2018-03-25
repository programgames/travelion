package com.example.alvillafra.travelion.vues.widgets;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.alvillafra.travelion.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import metier.ISouvenir;
import metier.IVoyage;

/**
 * Created by alicevillafranca on 24/03/2018.
 */

public class SouvenirsAdapter extends ArrayAdapter<ISouvenir> {


    public SouvenirsAdapter(@NonNull Context context, int resource, @NonNull List<ISouvenir> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ISouvenir s = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.travel_view_item,parent, false);
        }

        TextView tvNom = convertView.findViewById(R.id.view_name_travel);
        TextView tvLieu = convertView.findViewById(R.id.view_place_travel);

        tvNom.setText(s.getTitre());

        DateFormat format = new SimpleDateFormat("dd/MM/yy");

        tvLieu.setText(format.format(s.getDate()));


        return convertView;
    }
}
