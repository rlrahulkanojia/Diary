package com.example.root.app;

/**
 * Created by root on 12/6/17.
 */
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;


public class Menu_1 extends Fragment implements View.OnClickListener {

    private static final String PREFS_NAME = "prefs";
    private static final String PREF_DARK_THEME = "dark_theme";
    private FloatingActionButton Add,List_display;
   View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_menu_1, container, false);
        ///*
        SharedPreferences preferences = getActivity().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean useDarkTheme = preferences.getBoolean(PREF_DARK_THEME, false);

        if(useDarkTheme) {
            getActivity().setTheme(R.style.AppTheme_Dark_NoActionBar);
           // view.setBackgroundResource(R.color.darkColorPrimaryDark);
            view.setBackgroundResource(R.drawable.logo1);
            }
            else {
            view.setBackgroundResource(R.drawable.long123);
            //view.setBackgroundResource(R.color.primarywhile);
        }

        //*/


        List_display = (FloatingActionButton)view.findViewById(R.id.List_display);
        List_display.setImageResource(R.drawable.ic_list_24dp);
        List_display.setOnClickListener(this);
        Add = (FloatingActionButton)view.findViewById(R.id.Add);
        Add.setImageResource(R.drawable.ic_add_black_24dp);
        Add.setOnClickListener(this);

        return view;
        // /return inflater.inflate(R.layout.fragment_menu_1, container, false);

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Your Diary");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Add:
                Toast.makeText(getContext(), "Message", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), Notes.class);
                getActivity().startActivity(intent);
                break;
            case R.id.List_display:
                Log.v(TAG, "Chevk");
                Toast.makeText(getContext(), "Message 2", Toast.LENGTH_SHORT).show();
               // Intent intent1= new Intent(getActivity(), ViewListContents.class);
                //getActivity().startActivity(intent1);
                Intent view_user = new Intent(getContext(),ViewListContents.class);
                view_user.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                        | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(view_user);


                break;

        }
    }



}
