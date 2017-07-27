package com.example.root.app;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by root on 12/6/17.
 */

public class Menu_3 extends Fragment implements View.OnClickListener {
    View view;
    ImageButton email;
    Button rate;

    private static final String PREFS_NAME = "prefs";
    private static final String PREF_DARK_THEME = "dark_theme";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        view = inflater.inflate(R.layout.fragment_menu_3, container, false);

        SharedPreferences preferences = getActivity().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean useDarkTheme = preferences.getBoolean(PREF_DARK_THEME, false);

        if (useDarkTheme) {
            getActivity().setTheme(R.style.AppTheme_Dark_NoActionBar);
            view.setBackgroundResource(R.color.darkColorPrimaryDark);
        } else
            view.setBackgroundResource(R.color.white);


   //     rate = (Button) view.findViewById(R.id.rate);
        email = (ImageButton) view.findViewById(R.id.email);
        email.setOnClickListener(this);
     //   rate.setOnClickListener(this);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("About");
    }

    private boolean MyStartActivity(Intent aIntent) {
        try {
            startActivity(aIntent);
            return true;
        } catch (ActivityNotFoundException e) {
            return false;
        }
    }

    //On click event for rate this app button

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.email:
                //Toast.makeText(getContext(), "Email clicked", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setData(Uri.parse("Email"));
                String[] s = {"rlrahulkanojia.rk@gmail.com"};
                i.putExtra(Intent.EXTRA_EMAIL, s);
                i.putExtra(Intent.EXTRA_SUBJECT, "Issue in The Diary");
                i.setType("message/rfc822");
                Intent chooser = Intent.createChooser(i, "Launch Email");
                startActivity(chooser);
                break;
         /*   case R.id.rate:

                Intent intent = new Intent(Intent.ACTION_VIEW);
                //Try Google play
                intent.setData(Uri.parse("market://details?id=[Id]"));
                if (!MyStartActivity(intent)) {
                    //Market (Google play) app seems not installed, let's try to open a webbrowser
                    intent.setData(Uri.parse("https://play.google.com/store/apps/details?[Id]"));
                    if (!MyStartActivity(intent)) {
                        //Well if this also fails, we have run out of options, inform the user.
                        Toast.makeText(getContext(), "Could not open Android market, please install the market app.", Toast.LENGTH_SHORT).show();
                    }
                }
               break;
*/
        }
    }
}