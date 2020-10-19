package com.example.beermaker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Activity1 extends AppCompatActivity {
    private ArrayList<LinearLayout> listeParagraphes;
    int pointer;
    private ImageButton btBack, btNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1);
        init();
        initBtn();
    }

    private void init(){
        listeParagraphes = new ArrayList<>();
        listeParagraphes.add((LinearLayout) findViewById(R.id.par_0));
        listeParagraphes.add((LinearLayout) findViewById(R.id.par_1));
        listeParagraphes.add((LinearLayout) findViewById(R.id.par_2));
        listeParagraphes.add((LinearLayout) findViewById(R.id.par_3));
        listeParagraphes.add((LinearLayout) findViewById(R.id.par_4));
        listeParagraphes.add((LinearLayout) findViewById(R.id.par_5));
        listeParagraphes.add((LinearLayout) findViewById(R.id.par_6));
        listeParagraphes.add((LinearLayout) findViewById(R.id.par_7));
        for (LinearLayout layout:listeParagraphes) {
            layout.setVisibility(View.GONE);
        }
        listeParagraphes.get(0).setVisibility(View.VISIBLE);
        pointer = 0;
    }

    private void initBtn(){
        btBack = findViewById(R.id.back_btn);
        btBack.setVisibility(View.GONE);
        btNext = findViewById(R.id.next_btn);

        btBack.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (btBack.getVisibility()!=View.GONE){
                    try {
                        pointer--;
                        refreshPar();
                    }catch (Exception e){

                    }
                }
            }
        });

        btNext.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (btNext.getVisibility()!=View.GONE){
                    try {
                        pointer++;
                        refreshPar();
                    }catch (Exception e){

                    }
                }
            }
        });
    }

    private void refreshPar(){
        for (LinearLayout layout:listeParagraphes) {
            layout.setVisibility(View.GONE);
        }
        listeParagraphes.get(pointer).setVisibility(View.VISIBLE);
        refreshBt();
    }

    private void refreshBt(){
        if (pointer<=0){
            btBack.setVisibility(View.GONE);
        }else{
            btBack.setVisibility(View.VISIBLE);
        }
        if (pointer>=7){
            btNext.setVisibility(View.GONE);
        }else{
            btNext.setVisibility(View.VISIBLE);
        }
        System.out.println(pointer);
    }
}
