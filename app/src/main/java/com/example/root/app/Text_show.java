package com.example.root.app;

import android.content.SharedPreferences;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.ParseException;

/**
 * Created by root on 16/6/17.
 */

public class Text_show extends ViewListContents {
    private static final String PREFS_NAME = "prefs";
    private static final String PREF_DARK_THEME = "dark_theme";
    RelativeLayout ll;
    String passedVar;
    private TextView passedView = null;
    int index;
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onCreate(Bundle savedInstanceState) {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean useDarkTheme = preferences.getBoolean(PREF_DARK_THEME, false);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_layout);

        ll=(RelativeLayout)findViewById(R.id.text_show_layout);
        if(useDarkTheme) {
            setTheme(R.style.AppTheme_Dark_NoActionBar);
            ll.setBackgroundResource(R.color.darkColorPrimaryDark);
        }
        else
            ll.setBackgroundResource(R.color.white);



        passedVar = getIntent().getStringExtra("ID_EXTRA");
        passedView=(TextView)findViewById(R.id.text_view);
       // passedView.setText("You Clicked Item Np - "+ passedVar);
        Record rec=new Record();
        try {
            index = NumberFormat.getInstance().parse(passedVar).intValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        rec=myDB.Get_Record(index);
        passedView.setText(rec._text);
        myDB.close();
    }
}