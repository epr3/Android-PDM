package com.ase.eu.android_pdm;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.database.Cursor;

import java.util.List;

@Dao
public interface TraseuDAO {
    @Query("SELECT * FROM traseu")
    List<Traseu> getAllTrasee();

    @Insert
    void insert(Traseu traseu);

    @Insert
    void insertWithPuncte(Traseu traseu, List<Punct> listaPuncte);

    @Query("SELECT * FROM traseu t JOIN punct p ON t.id=p.traseuId")
    List<TraseuPuncte> getAllTraseePuncte();

    @Query("SELECT * FROM traseu")
    public Cursor selectCursorTrasee();
}
