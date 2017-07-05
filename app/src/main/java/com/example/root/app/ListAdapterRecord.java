package com.example.root.app;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by root on 29/6/17.
 */

public class ListAdapterRecord extends ArrayAdapter<Record> {

    List<Record> lstRec;
    LayoutInflater inflater;


    public ListAdapterRecord(@NonNull Context context, @LayoutRes int resource, @NonNull List<Record> objects) {
        super(context, resource, objects);
        lstRec=objects;
        inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view=convertView;
        if(view==null){
            view=inflater.inflate(R.layout.listview_row,null);
        }
        Record record=lstRec.get(position);
        TextView name=(TextView)view.findViewById(R.id.user_name_txt);
        TextView date=(TextView)view.findViewById(R.id.user_mob_txt);
       // TextView text=(TextView)view.findViewById(R.id.user_text_txt);
        name.setText(record._name);
        date.setText(record._date);
        //text.setText(record._text);
        return view;
}
}
