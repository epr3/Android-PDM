package com.ase.eu.android_pdm;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface PunctDAO {
    @Insert
    void insert(Punct punct);

    @Insert
    void insertAll(List<Punct> listaPuncte);

    @Insert
    void insertPuncteForTraseu(Traseu traseu, List<Punct> listaPuncte);

    @Query("SELECT * FROM punct")
    List<Punct> getListaPuncte();

    @Query("SELECT * FROM punct WHERE traseuId=:traseuId")
    List<Punct> getListaPuncteCuTraseu(final int traseuId);
}
