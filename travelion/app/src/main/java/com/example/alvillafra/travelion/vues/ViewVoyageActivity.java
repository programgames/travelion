package com.example.alvillafra.travelion.vues;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.alvillafra.travelion.R;
import com.example.alvillafra.travelion.vues.widgets.SouvenirsAdapter;

import java.text.SimpleDateFormat;

import metier.Exceptions.MissingArgumentException;
import metier.ISouvenir;
import metier.IVoyage;

/**
 * Created by alicevillafranca on 14/03/2018.
 */

public class ViewVoyageActivity extends AppCompatActivity {

    private int indexVoyage;
    public int getIndexVoyage(){
        return indexVoyage;
    }

    public IVoyage getVoyage(){
        return MainPage.getCarnet().getVoyages().get(getIndexVoyage());
    }

    private TextView nomVoyage;
    private TextView lieuVoyage;
    private TextView dateDepart;
    private TextView dateRetour;

    private ListView viewSouvenirs;
    private ArrayAdapter<ISouvenir> adapterSouvenir;

    private Button addSouvenir;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        indexVoyage = intent.getIntExtra("index",-1);
        if(indexVoyage == -1){
            intent.putExtra("error","Impossible de récupérer le voyage selectionné");
            setResult(RESULT_CANCELED,intent);
        }

        setContentView(R.layout.view_travel_page);



        nomVoyage = findViewById(R.id.nom_voyage_view);
        nomVoyage.setText(getVoyage().getNom());

        lieuVoyage = findViewById(R.id.lieu_voyage_view);
        lieuVoyage.setText(getVoyage().getNomLieu());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

        dateDepart = findViewById(R.id.date_depart_view);
        dateDepart.setText(dateFormat.format(getVoyage().getDateDepart()));


        dateRetour = findViewById(R.id.date_retour_view);
        dateRetour.setText(dateFormat.format(getVoyage().getDateRetour()));

        addSouvenir = findViewById(R.id.add_souvenir);
        addSouvenir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), AjouterSouvenirActivity.class);
                nextScreen.putExtra("index",indexVoyage);
                startActivityForResult(nextScreen,RESULT_OK);
            }
        });

        viewSouvenirs = findViewById(R.id.list_souvenirs_view);

        adapterSouvenir = new SouvenirsAdapter(this, android.R.layout.simple_list_item_1,getVoyage().getSouvenirs());
        viewSouvenirs.setAdapter(adapterSouvenir);
        viewSouvenirs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(),ViewSouvenirActivity.class);
                intent.putExtra("indexVoyage",indexVoyage);
                intent.putExtra("indexSouvenir",position);

                startActivityForResult(intent, RESULT_OK);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        setResult(RESULT_OK);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapterSouvenir.notifyDataSetChanged();
        Log.i("nbSouv", String.valueOf(adapterSouvenir.getCount()));

    }
}
