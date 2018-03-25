package com.example.alvillafra.travelion.vues;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.alvillafra.travelion.R;
import com.example.alvillafra.travelion.vues.widgets.MyDatePickerDialog;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import metier.Carnet;
import metier.IVoyage;
import metier.ToDoList;
import metier.Voyage;


/**
 * Created by alvillafra on 27/02/18.
 */

public class AddVoyagePage extends IDatePickerUsingActivity {

    private static final String tag = "AddVoyageActivity";
    private IVoyage nouveauVoyage;
    /*public IVoyage getNouveauVoyage(){
        return nouveauVoyage;
    }*/

    private PlaceAutocompleteFragment placeSelected;
    private Button addToDo;
    private EditText editNom;

    private EditText editDep;
    public boolean isEditDepSelected(){
        return (idSelected == editDep);
    }

    private EditText editRet;
    public boolean isEditRetSelected(){
        return (idSelected == editRet);
    }
    public EditText getEditRet() {return editRet;}

    private EditText idSelected;
    public EditText getIdSelected(){
        return idSelected;
    }

    private Button addButton;
    private ToDoList todo;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_travel_page);
        todo = new ToDoList();
        nouveauVoyage = new Voyage();

        placeSelected = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        placeSelected.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                nouveauVoyage.setLatitude(place.getLatLng().latitude);
                nouveauVoyage.setLongitude(place.getLatLng().longitude);
                nouveauVoyage.setNomLieu((String) place.getName());
            }
            @Override
            public void onError(Status status) {
                Log.i("Place error", "Ah ! problème !");
            }
        });
        AutocompleteFilter citiesFilter = new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES)
                .build();
        placeSelected.setFilter(citiesFilter);
        AutocompleteFilter regionsFilter = new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_REGIONS)
                .build();
        placeSelected.setFilter(regionsFilter);

        editNom = findViewById(R.id.nom_voyage_editText);
        editNom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                nouveauVoyage.setNom(s.toString());
                Log.i("NomVoyage",s.toString());
            }
        });

        editDep = findViewById(R.id.editTextDateDepart);
        editDep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idSelected = editDep;
                showDatePicker((EditText) findViewById(R.id.editTextDateDepart),v);
            }
        });
        addToDo = (Button) findViewById(R.id.addToDo);
        addToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(tag,"LANCEMENT DE LAJOUT TODO");
                Intent nextScreen = new Intent(getApplicationContext(), AddToDoActivity.class);
                nextScreen.putExtra("todo",todo);

                startActivityForResult(nextScreen,RESULT_OK);

            }
        });
        editRet = findViewById(R.id.editTextDateRetour);
        editRet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idSelected = editRet;
                showDatePicker((EditText) findViewById(R.id.editTextDateRetour),v);
            }
        });
        /*InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editRet.getWindowToken(), 0);*/
        editDep.setFocusable(false);
        editDep.setInputType(InputType.TYPE_NULL);
        editDep.invalidate();
        editRet.setFocusable(false);
        editRet.setInputType(InputType.TYPE_NULL);
        editRet.invalidate();


        addButton = findViewById(R.id.addVoyage);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Carnet c = MainPage.getCarnet();
                try{
                    c.addVoyage(nouveauVoyage);
                    Log.i("Ajout","Lae voyage est sensé être ajouté");
                    finish();
                }
                catch(Exception e){
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddVoyagePage.this);

                    builder.setMessage(e.getMessage())
                            .setTitle("Une erreur s'est produite");
                    AlertDialog a = builder.create();
                    a.show();
                }
            }
        });





    }




    public void changeDate(Date d) throws Exception{


        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
        if(idSelected != null){
            if (idSelected == editDep){
                    nouveauVoyage.setDateDepart(d);
            }
            else{

                nouveauVoyage.setDateRetour(d);
            }
        }

        String s = df.format(d);
        idSelected.setText(s);
        idSelected = null;

    }

    @Override
    public Date getDatePickerDate() {
        if(idSelected != null){
            if (isEditDepSelected()){
                return nouveauVoyage.getDateDepart();
            }
            if(isEditRetSelected()){

                return nouveauVoyage.getDateRetour();
            }
        }
        return null;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == RESULT_OK) {

            if (resultCode == RESULT_OK) {

            }
        }
    }

}
