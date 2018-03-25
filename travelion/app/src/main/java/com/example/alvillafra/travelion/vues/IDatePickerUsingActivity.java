package com.example.alvillafra.travelion.vues;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.alvillafra.travelion.vues.widgets.MyDatePickerDialog;

import java.util.Date;

/**
 * Created by alicevillafranca on 24/03/2018.
 */

public abstract class IDatePickerUsingActivity extends AppCompatActivity{

    public abstract void changeDate(Date d) throws Exception;

    public abstract Date getDatePickerDate();

    void showDatePicker(EditText edit, View view){

        MyDatePickerDialog f = MyDatePickerDialog.newInstance();

        f.show(getFragmentManager(),"dialog");
            /*Intent intent = new Intent(view.getContext(),MyDatePickerDialog.class);
        startActivity(intent);*/
    }

}
