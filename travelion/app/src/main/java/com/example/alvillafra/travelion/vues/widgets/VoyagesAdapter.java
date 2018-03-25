package com.example.alvillafra.travelion.vues.widgets;

import android.content.Context;
import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.alvillafra.travelion.R;

import metier.GetVoyagesCommande.IVoyagesCommande;
import metier.IVoyage;

/**
 * Created by alicevillafranca on 21/03/2018.
 */

public class VoyagesAdapter implements ListAdapter {

    private IVoyagesCommande commande;
    private Context context;

    private DataSetObservable observable;

    public VoyagesAdapter(@NonNull Context context, IVoyagesCommande commande, DataSetObservable observable) {
        this.commande = commande;
        this.context = context;
        this.observable = observable;

    }


    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
    observable.registerObserver(observer);

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
    observable.unregisterObserver(observer);
    }

    @Override
    public int getCount() {
        return commande.getVoyages().size();
    }

    @Nullable
    @Override
    public IVoyage getItem(int position) {
        return commande.getVoyages().get(position);


    }

    @Override
    public long getItemId(int position) {
        return R.layout.travel_view_item;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        IVoyage v = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.travel_view_item,parent, false);
        }

        TextView tvNom = convertView.findViewById(R.id.view_name_travel);
        TextView tvLieu = convertView.findViewById(R.id.view_place_travel);

        tvNom.setText(v.getNom());
        tvLieu.setText(v.getNomLieu());


        return convertView;
    }

    @Override
    public int getItemViewType(int position) {

        return 1;
    }

    @Override
    public int getViewTypeCount() {

        return 1;
    }

    @Override
    public boolean isEmpty() {

        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    public void notifyDataSetChanged(){
        observable.notifyChanged();
    }
}
