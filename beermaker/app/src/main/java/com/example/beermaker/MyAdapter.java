package com.example.beermaker;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    public interface Listener {
        void onClickButton(int position);
    }

    private final Listener callback;
    private List<Recette> list;
    private LocalAccess localAccess;

    //ajouter un constructeur prenant en entrée une liste
    public MyAdapter(Listener callback, List<Recette> list, LocalAccess localAccess) {
        this.callback = callback;
        this.list = list;
        this.localAccess = localAccess;
    }

    //cette fonction permet de créer les viewHolder
    //et par la même indiquer la vue à inflater (à partir des layout xml)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lignerecette,viewGroup,false);
        return new MyViewHolder(view, localAccess);
    }

    //c'est ici que nous allons remplir notre cellule avec le texte/image de chaque MyObjects
    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        Recette recette = list.get(position);
        myViewHolder.bind(recette, callback);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }




}
