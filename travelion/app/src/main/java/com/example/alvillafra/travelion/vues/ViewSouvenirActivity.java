package com.example.alvillafra.travelion.vues;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alvillafra.travelion.R;

import metier.IVoyage;

/**
 * Created by jumarcon1 on 13/03/18.
 */

public class ViewSouvenirActivity extends AppCompatActivity {


    private int indexVoyage;
    private IVoyage getVoyage(){
        return MainPage.getCarnet().getVoyages().get(indexVoyage);
    }
    private static final String tag = "ViewSouvenirActivity";
    private float x1, x2;
    static final int MIN_DISTANCE = 150;
    TextView titre;
    TextView anecdote;
    TextView index;
    TextView date;
    ImageView image;
    Button btnOpen;
    int indexSouvenir;
    int taille;

    Runnable updateTextView = new Runnable() {

        @Override
        public void run() {

        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent intent = getIntent();
        indexVoyage = intent.getIntExtra("indexVoyage",-1);
        indexSouvenir = intent.getIntExtra("indexSouvenir",-1);

        if(indexVoyage ==-1 || indexSouvenir == -1){
            intent.putExtra("error","Impossible de récupérer le voyage selectionné");
            setResult(RESULT_CANCELED,intent);
        }


        setContentView(R.layout.viewsouvenir);
        findViewByIds();



        btnOpen.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Closing SecondScreen Activity

                Intent nextScreen = new Intent(getApplicationContext(), AjouterSouvenirActivity.class);
                nextScreen.putExtra("index", indexVoyage);
                startActivityForResult(nextScreen, 0);

            }
        });

        Intent i = getIntent();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {



    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        index.setVisibility(View.VISIBLE);
        anecdote.setVisibility(View.VISIBLE);
        titre.setVisibility(View.VISIBLE);
        image.setVisibility(View.VISIBLE);
        date.setVisibility(View.VISIBLE);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;

                if (Math.abs(deltaX) > MIN_DISTANCE) {
                    // Left to Right swipe action
                    if (x2 < x1) {



                        Log.i(tag, "left");

                        taille = getVoyage().getSouvenirs().size();
                        Log.i(tag,"taille = " + taille + "index = " + indexSouvenir);
                        if (indexSouvenir + 1 < taille) {



                            indexSouvenir++;
                            index.setText(Integer.toString(indexSouvenir));
                            date.setText(getVoyage().getSouvenirs().get(indexSouvenir).getDate().toString());
                            anecdote.setText(getVoyage().getSouvenirs().get(indexSouvenir).getAnecdote());
                            titre.setText(getVoyage().getSouvenirs().get(indexSouvenir).getTitre().toString());
                            image.setImageBitmap(getVoyage().getSouvenirs().get(indexSouvenir).getImage().getImage());


                        }

                    } else {
                        Log.i(tag, "right");
                        taille = getVoyage().getSouvenirs().size();
                        Log.i(tag,"taille = " + taille + "index = " + indexSouvenir);
                        if (taille > 0) {

                            if (indexSouvenir - 1 > 0) {

                                if (getVoyage().getSouvenirs().get(indexSouvenir - 1) != null) {
                                    indexSouvenir--;
                                    index.setText(Integer.toString(indexSouvenir));
                                    anecdote.setText(getVoyage().getSouvenirs().get(indexSouvenir).getAnecdote());
                                    titre.setText(getVoyage().getSouvenirs().get(indexSouvenir).getTitre());
                                    image.setImageBitmap(getVoyage().getSouvenirs().get(indexSouvenir).getImage().getImage());
                                    date.setText(getVoyage().getSouvenirs().get(indexSouvenir).getDate().toString());
                                }


                            }

                        } else {
                            index.setVisibility(View.GONE);
                            anecdote.setVisibility(View.GONE);
                            titre.setVisibility(View.GONE);
                            image.setVisibility(View.GONE);
                            date.setVisibility(View.GONE);
                            btnOpen.setVisibility(View.VISIBLE);

                        }


                    }
                }
        }
        return super.onTouchEvent(event);
    }



    public void findViewByIds() {

        titre = (TextView) findViewById(R.id.Titre);
        image =(ImageView) findViewById(R.id.imageS);
        index = (TextView) findViewById(R.id.Index);
        date = (TextView) findViewById(R.id.Date);
        anecdote = (TextView) findViewById(R.id.souvenir);
         btnOpen = (Button) findViewById(R.id.btnOpen);

    }

    public void update(){

        runOnUiThread(updateTextView);

    }

}



