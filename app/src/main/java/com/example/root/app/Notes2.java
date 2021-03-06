package com.example.root.app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by root on 13/6/17.
 */

public class Notes2 extends AppCompatActivity {
    DatabaseHelper myDB;
    public String result,DateToStr;
    Context context=this;
    private static final String PREFS_NAME = "prefs";
    private static final String PREF_DARK_THEME = "dark_theme";
    EditText Story;
    RelativeLayout ll;

    EditText userInput;
    String temp;
    int index2;
    String passedVar2;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        // 26 june

        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean useDarkTheme = preferences.getBoolean(PREF_DARK_THEME, false);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes2);
        Story=(EditText)findViewById(R.id.Story2);
        ll = (RelativeLayout)findViewById(R.id.linearLayout_notes2);

        if(useDarkTheme) {
            setTheme(R.style.AppTheme_Dark_NoActionBar);
            ll.setBackgroundResource(R.color.darkColorPrimaryDark);
            Story.setTextColor(ContextCompat.getColor(context,R.color.white));
        }
        else {
            ll.setBackgroundResource(R.color.white);
            Story.setTextColor(ContextCompat.getColor(context,R.color.textColor));
        }
        // 26 june

        myDB =new DatabaseHelper(this);
        Record rec;
        passedVar2 = getIntent().getStringExtra("ID_EXTRA2");
        try {
            index2 = NumberFormat.getInstance().parse(passedVar2).intValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        rec=myDB.Get_Record(index2);
        Story.setText(rec._text);




    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void save(View view){

        passedVar2 = getIntent().getStringExtra("ID_EXTRA2");
        myDB =new DatabaseHelper(this);
        Record rec;

        try {
            index2 = NumberFormat.getInstance().parse(passedVar2).intValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        rec=myDB.Get_Record(index2);

        Date curDate = new Date();
        final SimpleDateFormat format = new SimpleDateFormat("dd-MM-yy hh:mm a");
        DateToStr = format.format(curDate);
        // Toast.makeText(getApplicationContext(),DateToStr,Toast.LENGTH_SHORT).show();



        //code of prompt goes here
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.prompt, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(promptsView);

        userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);

        userInput.setText(rec._name);

        // set dialog message
        temp=Story.getText().toString();
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // get user input and set it to result
                                // edit text
                                result = userInput.getText().toString();
                                //result = result+"  "+DateToStr;

                                //String TEMP= " CHeck";
                                Update(result,DateToStr,temp);
                             //   Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                finish();

                            }

                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                finish();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();


    }


    public void discard(View view){

        // 30 june

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        //builder.setTitle("Discard?");
        builder.setMessage("Do you want to Discard Entry ?");
        builder.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

        //30 june


        Toast.makeText(getApplicationContext(),"Discard pressed",Toast.LENGTH_SHORT).show();
        //finish();


    }
    public void Update(String item1,String item2, String item3){
        //,String item2) {
        passedVar2 = getIntent().getStringExtra("ID_EXTRA2");
   int id=Integer.parseInt(passedVar2);
        myDB.Update_Record(new Record(id,item1,item2,item3));
    }


}