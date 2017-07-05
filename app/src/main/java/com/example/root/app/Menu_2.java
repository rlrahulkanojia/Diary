package com.example.root.app;

/**
 * Created by root on 12/6/17.
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

public class Menu_2 extends Fragment {

    private static final String PREFS_NAME = "prefs";
    private static final String PREF_DARK_THEME = "dark_theme";
    Switch toggle;
    TextView account;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        SharedPreferences preferences = getActivity().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean useDarkTheme = preferences.getBoolean(PREF_DARK_THEME, false);

        if (useDarkTheme) {
            getActivity().setTheme(R.style.AppTheme_Dark_NoActionBar);
        }


        View view = inflater.inflate(R.layout.fragment_menu_2, container, false);
        toggle = (Switch) view.findViewById(R.id.toggle);
        toggle.setChecked(useDarkTheme);
        if (toggle.isChecked()) {
            view.setBackgroundResource(R.color.darkColorPrimaryDark);
        } else
            view.setBackgroundResource(R.color.white);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                toggleTheme(isChecked);
                if (toggle.isChecked())
                    Toast.makeText(getContext(), "Toggle Checked", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getContext(), "Toggle UnChecked", Toast.LENGTH_SHORT).show();
            }
        });



        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Settings");
    }

    private void toggleTheme(boolean darkTheme) {
        SharedPreferences.Editor editor = getActivity().getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putBoolean(PREF_DARK_THEME, darkTheme);
        editor.apply();

        Intent intent = getActivity().getIntent();
        getActivity().finish();

        startActivity(intent);
    }
/*
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.account:
                Intent i = new Intent(getContext(), Account.class);
                startActivity(i);
                break;
        }*/
    }

