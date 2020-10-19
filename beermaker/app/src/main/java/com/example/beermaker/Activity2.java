package com.example.beermaker;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Activity2 extends AppCompatActivity {
    private Button outils_bt_cal, outils_bt_save;
    private EditText textZone_1, textZone_2, textZone_3;
    private TextView outil_txt_1, outil_txt_2, outil_txt_3,outil_txt_4,outil_txt_5,outil_txt_6;
    private TextView outil_des_1, outil_des_2,outil_des_3,outil_des_4,outil_des_5,outil_des_6,outil_des_8,outil_des_9,outil_des_10, outils_color_text;
    private ConstraintLayout outils_zone_cal, outils_color_box;

    boolean gotExtra;

    private LocalAccess localAccess = new LocalAccess(this);

    public Recette recette;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);
        initView();
        initBt();
        getExtra();
        if (gotExtra){
            showUnites();
            textZone_1.setText(""+recette.volAlc);
            textZone_2.setText(""+recette.degAlc);
            textZone_3.setText(""+recette.ebc);
        }

    }


    private void initView(){
        outils_bt_cal = findViewById(R.id.outils_bt_cal);
        outils_bt_save = findViewById(R.id.outils_bt_save);
        textZone_1 = findViewById(R.id.editText_1);
        textZone_2 = findViewById(R.id.editText_2);
        textZone_3 = findViewById(R.id.editText_3);
        outil_txt_1 = findViewById(R.id.outils_txt_1);
        outil_txt_2 = findViewById(R.id.outils_txt_2);
        outil_txt_3 = findViewById(R.id.outils_txt_3);
        outil_txt_4 = findViewById(R.id.outils_txt_4);
        outil_txt_5 = findViewById(R.id.outils_txt_5);
        outil_txt_6 = findViewById(R.id.outils_txt_6);
        outil_des_1 = findViewById(R.id.outils_des_1);
        outil_des_2 = findViewById(R.id.outils_des_2);
        outil_des_3 = findViewById(R.id.outils_des_3);
        outil_des_4 = findViewById(R.id.outils_des_4);
        outil_des_5 = findViewById(R.id.outils_des_5);
        outil_des_6 = findViewById(R.id.outils_des_6);
        outil_des_8 = findViewById(R.id.outils_des_8);
        outil_des_9 = findViewById(R.id.outils_des_9);
        outil_des_10 = findViewById(R.id.outils_des_10);
        outils_color_text = findViewById(R.id.outils_color_text);
        outils_zone_cal = findViewById(R.id.outils_zone_cal);
        outils_zone_cal.setVisibility(View.GONE);
        outils_color_box = findViewById(R.id.outils_color_box);

    }

    private void initBt(){
        outils_bt_cal.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    outils_zone_cal.setVisibility(View.VISIBLE);
                    if (!textZone_1.getText().toString().equals("") && !textZone_2.getText().toString().equals("") && !textZone_3.getText().toString().equals("")){
                        try {
                            recette=new Recette(localAccess.getLastId());
                            recette.volAlc = Integer.parseInt(textZone_1.getText().toString());
                            recette.degAlc = Integer.parseInt(textZone_2.getText().toString());
                            recette.ebc = Integer.parseInt(textZone_3.getText().toString());
                            calculUnites();
                        }catch (Exception conv_nb){
                            textZone_1.clearComposingText();
                            textZone_2.clearComposingText();
                            textZone_3.clearComposingText();
                            Toast.makeText(Activity2.this, "Veuillez entrer des nombres entiers", Toast.LENGTH_LONG).show();
                            outils_zone_cal.setVisibility(View.GONE);
                        }
                    }else{
                        Toast.makeText(Activity2.this, "Veuillez entrer les valeurs désirées", Toast.LENGTH_SHORT).show();
                        outils_zone_cal.setVisibility(View.GONE);
                    }
                }catch (Exception e){
                    outils_zone_cal.setVisibility(View.GONE);

                }

            }
        });
        outils_bt_save.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    if (recette!=null){
                        localAccess.add(recette.volAlc, recette.degAlc, recette.ebc, recette.malt, recette.eauBra, recette.eauRin, recette.houblonAm, recette.houblonAr, recette.levure, recette.color, recette.mcu, recette.srm);
                    }
                }catch (Exception e){
                    Toast.makeText(Activity2.this, "Erreur lors de la sauvegarde de la recette", Toast.LENGTH_LONG).show();
                    System.out.println(e);
                }
            }
        });
    }

    private void getExtra(){
        try {
            Bundle extras = getIntent().getExtras();
            int position = (int) extras.getSerializable("recette");
            recette = (Recette) localAccess.getById(position);
            gotExtra=true;
        } catch (Exception e) {
            gotExtra=false;
        }
    }

    private void calculUnites(){
        recette.calculUnites();
        showUnites();

    }

    private void showUnites(){
        outil_des_1.setText(getResources().getString(R.string.outils_des_1)+recette.malt+" kg");
        outil_des_2.setText(getResources().getString(R.string.outils_des_2)+recette.eauBra+" L");
        outil_des_3.setText(getResources().getString(R.string.outils_des_3)+recette.eauRin+" L");
        outil_des_4.setText(getResources().getString(R.string.outils_des_4)+recette.houblonAm+" g");
        outil_des_5.setText(getResources().getString(R.string.outils_des_5)+recette.houblonAr+" g");
        outil_des_6.setText(getResources().getString(R.string.outils_des_6)+recette.levure+" g");
        outil_des_8.setText(getResources().getString(R.string.outils_des_8)+recette.mcu+"");
        outil_des_9.setText(getResources().getString(R.string.outils_des_9)+recette.ebc+"");
        outil_des_10.setText(getResources().getString(R.string.outils_des_10)+recette.srm+"");
        outils_color_text.setText(recette.color);
        outils_color_box.setBackgroundColor(Color.parseColor(recette.color));
        outils_zone_cal.setVisibility(View.VISIBLE);
    }
}
