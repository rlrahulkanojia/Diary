package com.example.root.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import static java.lang.Thread.sleep;

public class Spash extends AppCompatActivity {

    ProgressBar pgBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash);//check
        pgBar=(ProgressBar)findViewById(R.id.PBar);
        Thread myThread=new Thread(){
        @Override
        public void run(){
            for(int i=0;i<100;){
                try{
                   sleep(100);  // modify
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                pgBar.setProgress(i);
                i=i+10;
            }
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }
        };
        myThread.start();
    }
}
