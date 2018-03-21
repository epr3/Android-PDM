package com.ase.eu.android_pdm;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface TraseuDAO {
    @Query("SELECT * FROM traseu")
    List<Traseu> getAllTrasee();

    @Insert
    void insert(Traseu traseu);

    @Query("SELECT * FROM traseu")
    List<TraseuPuncte> getAllTraseePuncte();

}
