package com.example.beermaker;


import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;


public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private TextView id, color;
    private ConstraintLayout color_box;
    private Button bt_to_recette, bt_remove_recette;

    private WeakReference<MyAdapter.Listener> callbackWeakRef;
    private LocalAccess localAccess;

    //itemView est la vue correspondante à 1 cellule
    public MyViewHolder(View itemView, LocalAccess localAccess) {
        super(itemView);
        id = itemView.findViewById(R.id.id_recette);
        color = itemView.findViewById(R.id.color_recette);
        color_box = itemView.findViewById(R.id.color_box);
        bt_to_recette = itemView.findViewById(R.id.bt_to_recette);
        bt_remove_recette = itemView.findViewById(R.id.bt_remove_recette);

    }

    //puis ajouter une fonction pour remplir la cellule en fonction d'un MyObject
    //callback// 2 - add callback reference to the signature
    public void bind(Recette recette, MyAdapter.Listener callback){
        id.setText(""+recette.getId());
        color.setText(recette.color);
        color_box.setBackgroundColor(Color.parseColor(recette.color));
        bt_to_recette.setOnClickListener(this);
        bt_remove_recette.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    localAccess.removeById(Integer.parseInt(id.getText().toString()));
                }catch (Exception e){

                }
            }
        });

        this.callbackWeakRef = new WeakReference<MyAdapter.Listener>(callback);


    }

    @Override
    public void onClick(View view) {
        MyAdapter.Listener callback = callbackWeakRef.get();
        Integer position = 0;
        try {
            position = Integer.parseInt(id.getText().toString());
        }catch (Exception e){
            position = null;
        }
        if (callback != null && position!=null){
            callback.onClickButton(position);
        }else{
            System.out.println("ERREUR LORS DU TRANSFERT DE L'ID");
        }
    }
}