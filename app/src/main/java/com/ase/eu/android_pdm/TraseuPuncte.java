package com.ase.eu.android_pdm;


import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

public class TraseuPuncte {
    @Embedded
    Traseu traseu;

    @Relation(parentColumn = "id", entityColumn = "traseuId")
    List<Punct> listaPuncte;

    public Traseu getTraseu() {
        return traseu;
    }

    public void setTraseu(Traseu traseu) {
        this.traseu = traseu;
    }

    public List<Punct> getListaPuncte() {
        return listaPuncte;
    }

    public void setListaPuncte(List<Punct> listaPuncte) {
        this.listaPuncte = listaPuncte;
    }
}
