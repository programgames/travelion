package com.example.alvillafra.travelion.vues;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;

import com.example.alvillafra.travelion.R;

import metier.ToDoList;

import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by jumarcon1 on 07/02/18.
 */

public class AddToDoActivity extends AppCompatActivity {

    public EditText nomToDo;
    public EditText textDate;
    public Button buttonToDo;
    public Button buttonEnregistrer;
    public TableLayout tableLayout;
    TableRow tbrow0 ;
    TextView tv0 ;
    ToDoList todo;


    private static final String tag = "AddToDoActivity";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        Log.i(tag,"PROBLEME");
        Intent i = getIntent();
        todo = (ToDoList) i.getSerializableExtra("todo");
        Log.i(tag,"TOUT EST BIEN ICI");

        tbrow0 = new TableRow(this);
        tv0 = new TextView(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_add_to_do);

        findViewByIds();


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
        SharedPreferences toto = this.getPreferences(MODE_PRIVATE);


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


                break;
            case 1:

                break;

        }
    }



    public void findViewByIds()
    {

        nomToDo =  (EditText) findViewById(R.id.ETODO);
        textDate = (EditText) findViewById(R.id.editTextDate);
        buttonToDo = (Button) findViewById(R.id.ButtonToDo);
        tableLayout = (TableLayout) findViewById(R.id.blocCheck);
        buttonEnregistrer = (Button) findViewById(R.id.EnregistrerButton);
        buttonEnregistrer.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Log.i(tag,"enregistrement");
               sauvegarde(v);

            }
        });
        buttonToDo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Log.i(tag,"ajout checkbox");
                ajoutToDo(v);

            }
        });


    }
    public void sauvegarde(View v)
    {

        Intent output = new Intent();
        output.putExtra("todo",todo);

        setResult(RESULT_OK, output);
        finish();
    }
    public void ajoutToDo(View v)
    {

        tv0.setText(" Sl.No ");
        tv0.setTextColor(Color.WHITE);
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText(" Product ");
        tv1.setTextColor(Color.WHITE);
        tbrow0.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText(" Unit Price ");
        tv2.setTextColor(Color.WHITE);
        tbrow0.addView(tv2);
        TextView tv3 = new TextView(this);
        tv3.setText(" Stock Remaining ");
        tv3.setTextColor(Color.WHITE);
        tbrow0.addView(tv3);
        tableLayout.addView(tbrow0);
        for (int i = 0; i < 25; i++) {
            TableRow tbrow = new TableRow(this);
            TextView t1v = new TextView(this);
            t1v.setText("" + i);
            t1v.setTextColor(Color.WHITE);
            t1v.setGravity(Gravity.CENTER);
            tbrow.addView(t1v);
            TextView t2v = new TextView(this);
            t2v.setText("Product " + i);
            t2v.setTextColor(Color.WHITE);
            t2v.setGravity(Gravity.CENTER);
            tbrow.addView(t2v);
            TextView t3v = new TextView(this);
            t3v.setText("Rs." + i);
            t3v.setTextColor(Color.WHITE);
            t3v.setGravity(Gravity.CENTER);
            tbrow.addView(t3v);
            TextView t4v = new TextView(this);
            t4v.setText("" + i * 15 / 32 * 10);
            t4v.setTextColor(Color.WHITE);
            t4v.setGravity(Gravity.CENTER);
            tbrow.addView(t4v);
            tableLayout.addView(tbrow);
        }
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {



        super.onSaveInstanceState(savedInstanceState);

    }


}
