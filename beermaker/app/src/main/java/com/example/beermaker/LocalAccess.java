package com.example.beermaker;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


public class LocalAccess {

    //propriété
    private String nomDB = "beermaker.sqlite";
    private Integer version = 1;
    private MySQLiteOpenHelper accesBD;
    private SQLiteDatabase database;


    public LocalAccess(Context contexte){
        accesBD = new MySQLiteOpenHelper(contexte, nomDB, null, version);
    }

    public void add(int volAlc, int degAlc, double ebc, double malt, double eauBra, double eauRin, double houblonAm, double houblonAr, double levure, String color, double mcu, double srm){
        database = accesBD.getWritableDatabase();
        String request = " Insert into recettes (id, volAlc, degAlc, ebc, malt, eauBra, eauRin, houblonAm, houblonAr, levure, color, mcu, srm) values ";
        Integer id =  getLastId()+1;
        request += "( "+id +" ,"+volAlc +" ,"+degAlc +" ,"+ebc +", "+malt +" ,"+eauBra +" ,"+eauRin +", "+houblonAm +" ,"+houblonAr +" ,"+levure +", \""+color+"\" , "+mcu+" ,"+srm+");";
        database.execSQL(request);
    }

    public void clear(){
        database = accesBD.getWritableDatabase();
        String request = "delete from recettes";
        database.execSQL(request);
    }

    public void removeById(int idRecette){
        database = accesBD.getWritableDatabase();
        String request = "DELETE FROM recettes WHERE id="+idRecette+";";
        database.execSQL(request);
    }

    public Recette getById(int idRecette){
        System.out.println("ID_RECETTE : "+idRecette);
        database = accesBD.getWritableDatabase();
        String request = "SELECT * FROM recettes WHERE id="+idRecette+";";
        Cursor curseur = database.rawQuery(request, null);
        curseur.moveToFirst();
        Integer id = curseur.getInt(0);
        int volAlc = curseur.getInt(1);
        int degAlc = curseur.getInt(2);
        Double ebc = curseur.getDouble(3);
        Double malt = curseur.getDouble(4);
        Double eauBra = curseur.getDouble(5);
        Double eauRin = curseur.getDouble(6);
        Double houblonAm = curseur.getDouble(7);
        Double houblonAr = curseur.getDouble(8);
        Double levure = curseur.getDouble(9);
        String color = curseur.getString(10);
        Double mcu = curseur.getDouble(11);
        Double srm = curseur.getDouble(12);
        Recette recette = new Recette(id, volAlc, degAlc, ebc, malt, eauBra, eauRin, houblonAm, houblonAr, levure, color, mcu, srm);
        return recette;
    }

    public Integer getLastId(){
        Integer id = 0;
        database = accesBD.getReadableDatabase();
        String request = "select * from recettes;";
        Cursor curseur = database.rawQuery(request, null);
        curseur.moveToLast();
        if (!curseur.isAfterLast()){
           id = curseur.getInt(0);
        }
        curseur.close();

        return id;
    }

    public Recette getLastSave(){
        database = accesBD.getReadableDatabase();
        String request = "select * from recettes;";
        Cursor curseur = database.rawQuery(request, null);
        Recette recette = null;
        curseur.moveToLast();
        if (!curseur.isAfterLast()){
            Integer id = curseur.getInt(0);
            int volAlc = curseur.getInt(1);
            int degAlc = curseur.getInt(2);
            Double ebc = curseur.getDouble(3);
            Double malt = curseur.getDouble(4);
            Double eauBra = curseur.getDouble(5);
            Double eauRin = curseur.getDouble(6);
            Double houblonAm = curseur.getDouble(7);
            Double houblonAr = curseur.getDouble(8);
            Double levure = curseur.getDouble(9);
            String color = curseur.getString(10);
            Double mcu = curseur.getDouble(11);
            Double srm = curseur.getDouble(12);
            recette = new Recette(id, volAlc, degAlc, ebc, malt, eauBra, eauRin, houblonAm, houblonAr, levure, color, mcu, srm);
        }
        curseur.close();

        return recette;
    }

    public ArrayList<Recette> getAll(){
        ArrayList<Recette> listeRecettes = new ArrayList<Recette>();
        database = accesBD.getReadableDatabase();
        String request = "select * from recettes;";
        Cursor curseur = database.rawQuery(request, null);
        Recette recette = null;

        curseur.moveToFirst();
        while (curseur.isAfterLast() == false)
        {
            Integer id = curseur.getInt(0);
            int volAlc = curseur.getInt(1);
            int degAlc = curseur.getInt(2);
            Double ebc = curseur.getDouble(3);
            Double malt = curseur.getDouble(4);
            Double eauBra = curseur.getDouble(5);
            Double eauRin = curseur.getDouble(6);
            Double houblonAm = curseur.getDouble(7);
            Double houblonAr = curseur.getDouble(8);
            Double levure = curseur.getDouble(9);
            String color = curseur.getString(10);
            Double mcu = curseur.getDouble(11);
            Double srm = curseur.getDouble(12);
            recette = new Recette(id, volAlc, degAlc, ebc, malt, eauBra, eauRin, houblonAm, houblonAr, levure, color, mcu, srm);
            listeRecettes.add(recette);
            curseur.moveToNext();
        }
        curseur.close();
        return listeRecettes;
    }

}
