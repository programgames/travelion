package com.example.alvillafra.travelion.vues.widgets;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.alvillafra.travelion.R;
import com.example.alvillafra.travelion.vues.AddVoyagePage;
import com.example.alvillafra.travelion.vues.IDatePickerUsingActivity;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by alvillafra on 28/02/18.
 */

public class MyDatePickerDialog extends DialogFragment {

    private Button cancelButton;
    private Button validateButton;
    private DatePicker datePicker;

    private IDatePickerUsingActivity activity;

    public static MyDatePickerDialog newInstance(){
        Fragment f =  new MyDatePickerDialog();

        return (MyDatePickerDialog) f;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.date_picker_layout,container,false);
        cancelButton  = (Button) v.findViewById(R.id.annuler);
        validateButton = (Button) v.findViewById(R.id.valider);
        datePicker = (DatePicker) v.findViewById(R.id.datePicker);

        Calendar c = Calendar.getInstance();
        activity = (IDatePickerUsingActivity)getActivity();

        Date d = activity.getDatePickerDate();
        if(d != null){

            c.setTime(d);
            datePicker.updateDate(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
        }


        return v;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Date d = new Date(datePicker.getYear()-1900,datePicker.getMonth(),datePicker.getDayOfMonth());
                Log.i("Year","Année : " + datePicker.getYear());
                activity = (IDatePickerUsingActivity) getActivity();
                try{
                    activity.changeDate(d);
                    dismiss();
                }
                catch(Exception e){
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);

                    builder.setMessage(e.getMessage())
                            .setTitle("Vérifiez vos dates");
                    AlertDialog a = builder.create();
                    a.show();
                }
            }
        });

    }





}
