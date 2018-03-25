package com.example.alvillafra.travelion.vues;


import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import com.example.alvillafra.travelion.R;

import metier.Exceptions.MissingArgumentException;
import metier.IVoyage;



import java.io.FileNotFoundException;
import java.io.InputStream;

import android.widget.PopupMenu;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by jumarcon1 on 07/02/18.
 */

public class AjouterSouvenirActivity extends IDatePickerUsingActivity {


    //private TextView fromDateEtxt;
    private EditText souvenirEdit;
    private EditText titreEdit;
    private EditText dateEditText;

    private int indexVoyage;

    private IVoyage getVoyage(){
        return MainPage.getCarnet().getVoyages().get(indexVoyage);
    }

    Bitmap image;
    public void setDate(Date date) {
        this.date = date;
    }
    private String titreS;
    private String dateS;
    private Date date = new Date();
    private SimpleDateFormat dateFormatter;
    public static final String PREFS_NAME = "MyPrefsVoyage";
    private static final String tag = "AjouterSouvenirActivity";
    private String anecdote;
    private ImageView imageView;
    Intent pickPhoto = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    Bundle theBundle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        Intent intent = getIntent();
        indexVoyage =intent.getIntExtra("index",-1);
        if(indexVoyage == -1){
            intent.putExtra("error","Impossible de récupérer le voyage selectionné");
            setResult(RESULT_CANCELED,intent);
        }
        dateFormatter = new SimpleDateFormat("dd/MM/yy", Locale.FRANCE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_memory);

        findViewByIds();

        InitializePersistance(savedInstanceState);



    }
    private void InitializePersistance(Bundle savedInstanceState) {


        if(savedInstanceState != null){

            souvenirEdit.setText(savedInstanceState.getString("anecdoteKey","anecdote"));
            titreEdit.setText(savedInstanceState.getString("titre1Key","titre"));
            dateEditText.setText(savedInstanceState.getString("dateKey",""));
            imageView.setImageBitmap((Bitmap) savedInstanceState.getParcelable("imageKey"));



        }

    }


    @Override
    protected void onPause(){

        super.onPause();
        Log.i(tag,"onSave");

    }
    @Override
    protected void onStart(){




        super.onStart();
        Log.i(tag,"onStart");

    }
    @Override
    protected void onDestroy()
    {
        Log.i(tag,"onDestroy");
        super.onDestroy();
    }

    protected void onSaveInstance()
    {
        Log.i(tag,"onSaveInstance");



    }

    protected void onRestore()
    {
        Log.i(tag,"onRestore");
    }
    @Override
    protected void onStop()
    {
        super.onStop();

        Log.i(tag,"onStop");

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode) {
            case 0:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = data.getData();
                    InputStream imageStream = null;
                    try {
                        imageStream = getContentResolver().openInputStream(selectedImage);
                        Bitmap photo = BitmapFactory.decodeStream(imageStream);
                        image = photo;
                        imageView.setImageBitmap(photo);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }

                break;
            case 1:
                if(resultCode == RESULT_OK){
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    image = photo;
                    imageView.setImageBitmap(photo);

                }
                break;

        }
    }

    public void showMenu(View v)
    {
        PopupMenu popup = new PopupMenu(this,v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.button_menu_photo, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Log.i(tag,item.getTitle().toString());
                if(item.getTitle().toString().equals("Appareil Photo"))
                {
                    startActivityForResult(takePicture, 1);
                }
                if(item.getTitle().toString().equals("Galerie"))
                {
                    startActivityForResult(pickPhoto, 0);
                }


                return true;
            }
        });
        popup.show();
    }

    public void findViewByIds()
    {

        dateEditText = (EditText) findViewById(R.id.etxt_fromdate);
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker((EditText) findViewById(R.id.editTextDateRetour),v);
            }
        });
        dateEditText.setFocusable(false);
        dateEditText.setInputType(InputType.TYPE_NULL);
        dateEditText.invalidate();


        souvenirEdit = (EditText) findViewById(R.id.souvenir);
        titreEdit =  (EditText) findViewById(R.id.titre);
        this.imageView = (ImageView)this.findViewById(R.id.imageS);

        Button menu=(Button)findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Log.i(tag,"boutonPhoto appuye");
                showMenu(v);

            }
        });
        Button retour = (Button) findViewById(R.id.buttonReturn);
        retour.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {




                //setResult(0,intentBack);
                setResult(RESULT_OK);
                finish();




            }
        });

        Button save = (Button)findViewById(R.id.buttonsave);

        save.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Log.d(tag,"Saving memory");
                if(imageView.getDrawable() == null)
                {
                    AlertDialog alertDialog = new AlertDialog.Builder(AjouterSouvenirActivity.this).create();
                    alertDialog.setTitle("Erreur");
                    alertDialog.setMessage("Pas d'image");
                    alertDialog.show();
                    return;
                }
                if(titreEdit.getText().toString().equals(""))
                {
                    AlertDialog alertDialog = new AlertDialog.Builder(AjouterSouvenirActivity.this).create();
                    alertDialog.setTitle("Erreur");
                    alertDialog.setMessage("Pas de titre");
                    alertDialog.show();
                    return;
                }
                if(souvenirEdit.getText().toString().equals(""))
                {
                    AlertDialog alertDialog = new AlertDialog.Builder(AjouterSouvenirActivity.this).create();
                    alertDialog.setTitle("Erreur");
                    alertDialog.setMessage("Pas d'anecdote");
                    alertDialog.show();
                    return;
                }
                if(dateEditText.getText().toString().equals(""))
                {
                    AlertDialog alertDialog = new AlertDialog.Builder(AjouterSouvenirActivity.this).create();
                    alertDialog.setTitle("Erreur");
                    alertDialog.setMessage("Pas de date");
                    alertDialog.show();
                    return;
                }
                saveMemory(v);

            }
        });
    }

    public void saveMemory(View v)
    {

        try{

            getVoyage().addSouvenir(((BitmapDrawable) imageView.getDrawable()).getBitmap(),date,titreEdit.getText().toString(),souvenirEdit.getText().toString());
            setResult(RESULT_OK);
            finish();

        }

        catch (MissingArgumentException e){

            AlertDialog alertDialog = new AlertDialog.Builder(AjouterSouvenirActivity.this).create();
            alertDialog.setTitle("Erreur");
            alertDialog.setMessage(e.getMessage());
            alertDialog.show();
        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {


        savedInstanceState.putString("anecdoteKey", anecdote);
        savedInstanceState.putString("titre1Key",titreS);
        savedInstanceState.putString("dateKey",date.toString());
        savedInstanceState.putParcelable("imageKey",image);
        super.onSaveInstanceState(savedInstanceState);
        Log.i(tag,"Memory saved");
    }


    @Override
    public void changeDate(Date d) throws Exception {

        if(d.compareTo(getVoyage().getDateDepart()) <0 || d.compareTo(getVoyage().getDateRetour())>0){
            throw new Exception("La date que vous avez entré est en dehors des dates du voyage");
        }

        date = d;
        dateEditText.setText(dateFormatter.format(d));
    }

    @Override
    public Date getDatePickerDate() {
        return date;
    }
}
