package com.example.root.app;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by root on 15/6/17.
 */

public class ViewListContents extends AppCompatActivity {
 Activity activity;
    DatabaseHelper myDB;
    private static final String PREFS_NAME = "prefs";
    private static final String PREF_DARK_THEME = "dark_theme";
    ListAdapterRecord rAdapter;
    ArrayList<HashMap<String, String>> myIds;
    LinearLayout ll;
    Context context=this;

//

//
    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean useDarkTheme = preferences.getBoolean(PREF_DARK_THEME, false);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewlistcontents);
        Toolbar toolbarTop = (Toolbar) findViewById(R.id.toolbar_top);
        ll = (LinearLayout)findViewById(R.id.view_list_layout);
        TextView mTitle = (TextView) toolbarTop.findViewById(R.id.toolbar_title);
        if(useDarkTheme) {
            setTheme(R.style.AppTheme_Dark_NoActionBar);
            ll.setBackgroundResource(R.color.darkColorPrimaryDark);
            toolbarTop.setBackgroundResource(R.color.darkColorPrimaryDark);
            mTitle.setTextColor(getResources().getColor(R.color.white,null));

        }
        else {
            ll.setBackgroundResource(R.color.white);
            toolbarTop.setBackgroundResource(R.color.white);
            mTitle.setTextColor(getResources().getColor(R.color.darkColorPrimaryDark,null));

        }

        // 26 june
        Log.v("Example", "onCreate");
        getIntent().setAction("Already created");



        mTitle.setText("Your Entries");

        ListView listView = (ListView) findViewById(R.id.listView);
        myDB = new DatabaseHelper(this);
        List<Record> lstRec= new ArrayList<Record>();

        lstRec.clear();
        myDB = new DatabaseHelper(this);
        ArrayList<Record> contact_array_from_db = myDB.Get_Records();

        for (int i = 0; i < contact_array_from_db.size(); i++) {

            int tidno = contact_array_from_db.get(i).getID();
            String name = contact_array_from_db.get(i).getName();
            String date = contact_array_from_db.get(i).getDate();
            //String text = contact_array_from_db.get(i).gettext();
            Record cnt = new Record();
            cnt.setID(tidno);
            cnt.setName(name);
            //cnt.settext(text);
            cnt.setDate(date);

            lstRec.add(cnt);
        }
        myDB.close();
        rAdapter = new ListAdapterRecord(ViewListContents.this, R.layout.listview_row,
                lstRec);
        listView.setAdapter(rAdapter);
        rAdapter.notifyDataSetChanged();

        registerForContextMenu(listView);
    }

    @Override
    protected void onResume() {
        Log.v("Example", "onResume");

        String action = getIntent().getAction();
        // Prevent endless loop by adding a unique action, don't restart if action is present
        if(action == null || !action.equals("Already created")) {
            Log.v("Example", "Force restart");
            Intent intent = new Intent(this, ViewListContents.class);
            startActivity(intent);
            finish();
        }
        // Remove the unique action so the next time onResume is called it will restart
        else
            getIntent().setAction(null);

        super.onResume();
    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);
        menu.setHeaderTitle("Select the action");
        menu.setHeaderIcon(R.drawable.ic_add_black_24dp);
        menu.add(0,v.getId(),0,"Delete");
        menu.add(0,v.getId(),0,"View");
        menu.add(0,v.getId(),0,"Edit");
    }


    @Override
    public boolean onContextItemSelected(final MenuItem item){
        if (item.getTitle()=="Delete")
        {

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setCancelable(true);
            //builder.setTitle("Discard?");
            builder.setMessage("Are you Confirm to Delete ?");
            builder.setPositiveButton("Confirm",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

                            myIds = myDB.selectAllIds();
                            int id = Integer.parseInt(myIds.get(info.position).get("id"));
                            myDB.Delete_Record(id);
                            finish();
                            startActivity(getIntent());

                           // Toast.makeText(this,"Delete Pressed",Toast.LENGTH_SHORT).show();
                        }
                    });
            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
 //  do nothing
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();



        }

        else  if (item.getTitle()=="View") {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

            myIds = myDB.selectAllIds();
            int id = Integer.parseInt(myIds.get(info.position).get("id"));
            Intent intent = new Intent(ViewListContents.this,Text_show.class);
            intent.putExtra("ID_EXTRA",String.valueOf(id));
            startActivity(intent);
       ///Toast.makeText(this, "View Pressed", Toast.LENGTH_SHORT).show();
        }

        else if (item.getTitle()=="Edit") {
          //  Toast.makeText(this, "Edit Pressed", Toast.LENGTH_SHORT).show();
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            myIds = myDB.selectAllIds();
            int id = Integer.parseInt(myIds.get(info.position).get("id"));
            String a="A";
            String b="B";
            String c="C";
            Intent i=new Intent(getApplicationContext(),Notes2.class);
            i.putExtra("ID_EXTRA2",String.valueOf(id));
            startActivity(i);
            myDB.close();
        }
        return true;
    }

}
