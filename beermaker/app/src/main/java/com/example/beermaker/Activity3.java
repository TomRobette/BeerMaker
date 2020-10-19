package com.example.beermaker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

public class Activity3 extends AppCompatActivity implements MyAdapter.Listener{
    private RecyclerView recyclerView;
    private LocalAccess localAccess = new LocalAccess(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        init();
    }

    private void init() {

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        System.out.println("taille : "+localAccess.getAll().get(2).color);
        recyclerView.setAdapter(new MyAdapter(this, localAccess.getAll(), localAccess));
    }

    @Override
    public void onClickButton(int position) {
        Intent intent = new Intent(Activity3.this, Activity2.class);
        intent.putExtra("recette", position);
        startActivity(intent);
    }
}