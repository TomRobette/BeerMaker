package com.example.beermaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button button, button2,button3;
    public static ArrayList<Button> listBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initBtn();
    }


    private void init() {
        button =findViewById(R.id.outils_bt_cal);
        button2 =findViewById(R.id.button2);
        button3 =findViewById(R.id.button3);
    }


    // fonction permettant l’ouverture d’une nouvelle activity en cliquant sur un bouton


    private void initBtn(){
        button.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, Activity1.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, Activity3.class);
                startActivity(intent);
            }
        });
    }



}

