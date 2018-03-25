package com.example.alvillafra.travelion.vues;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.alvillafra.travelion.R;
import com.example.alvillafra.travelion.vues.widgets.VoyagesAdapter;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.nearby.messages.internal.Update;

import java.io.Serializable;

import metier.Carnet;
import metier.GetVoyagesCommande.IVoyagesCommande;
import metier.GetVoyagesCommande.VoyagesEnCoursCommande;
import metier.GetVoyagesCommande.VoyagesFuturCommande;
import metier.GetVoyagesCommande.VoyagesPassesCommande;
import metier.IVoyage;
import persistance.CarnetPersistance;
import persistance.IPersistance;
import persistance.Stub;

/**
 * Created by alvillafra on 24/01/18.
 */
public class MainPage extends AppCompatActivity implements OnMapReadyCallback {


    private static Carnet lesVoyages;
    public static Carnet getCarnet(){return lesVoyages;}

    private GoogleMap map;


    private ListView listFutur;
    private ListView listEnCours;
    private ListView listPasse;

    private VoyagesAdapter adapterFutur;
    private VoyagesAdapter adapterEnCours;
    private VoyagesAdapter adapterPasse;

    private IPersistance pers;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        InitializePersistance(savedInstanceState);

        UpdateCarnet();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        InitializeMap();
        InitializeAdapters();


    }

    private void UpdateCarnet(){

    }

    private void InitializePersistance(Bundle savedInstanceState) {
        pers = new CarnetPersistance();
        //lesVoyages = pers.loadCarnet(this);

        if(savedInstanceState == null || savedInstanceState.get("carnet") == null){
            Log.i("pers","loadCarnet");
            //Persistance dure
            lesVoyages = pers.loadCarnet(this);
        }
        else{
            //Persistance Légère
            lesVoyages = (Carnet) savedInstanceState.get("carnet");
        }
    }

    private void InitializeMap(){
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }

    private void InitializeAdapters(){

        adapterFutur = new VoyagesAdapter(this,new VoyagesFuturCommande(), getCarnet());
        listFutur = findViewById(R.id.list_futur);
        listFutur.setAdapter(adapterFutur);
        listFutur.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDetail((IVoyage) parent.getItemAtPosition(position),view);
            }
        });

        adapterPasse = new VoyagesAdapter(this,new VoyagesPassesCommande(), getCarnet());
        listPasse = findViewById(R.id.list_passe);
        listPasse.setAdapter(adapterPasse);
        listPasse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDetail((IVoyage) parent.getItemAtPosition(position),view);
            }
        });

        adapterEnCours = new VoyagesAdapter(this, new VoyagesEnCoursCommande(), getCarnet());
        listEnCours = findViewById(R.id.list_cours);
        listEnCours.setAdapter(adapterEnCours);
        listEnCours.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDetail((IVoyage) parent.getItemAtPosition(position),view);
            }
        });

    }

    private void showDetail(IVoyage v,View view){
        Intent intent = new Intent(view.getContext(),ViewVoyageActivity.class);
        intent.putExtra("index", getCarnet().getVoyages().indexOf(v));

        startActivityForResult(intent, RESULT_OK);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        if(googleMap != null){
            map = googleMap;

            for(MarkerOptions mo : lesVoyages.getMarkers() ){
                googleMap.addMarker(mo);
            }
        }

    }


    @Override
    protected void onResume(){
        super.onResume();
        adapterFutur.notifyDataSetChanged();
        adapterEnCours.notifyDataSetChanged();
        adapterPasse.notifyDataSetChanged();
        onMapReady(map);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("carnet",getCarnet());
    }


    @Override
    protected void onStop() {
        pers.saveCarnet(getCarnet(),this);
        super.onStop();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    public void onClickAddVoyage(View view) {

        Intent intent = new Intent(view.getContext(),AddVoyagePage.class);

        startActivity(intent);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_CANCELED){
            String s = data.getStringExtra("error");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage(s)
                    .setTitle("Une erreur s'est produite");
            AlertDialog a = builder.create();
            a.show();
        }
    }
}
