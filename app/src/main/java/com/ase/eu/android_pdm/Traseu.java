package com.ase.eu.android_pdm;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity(tableName = "traseu")
public class Traseu implements Serializable{

   @PrimaryKey(autoGenerate = true)
    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    private String denumire;
    private Date dataStart;
    private Date dataFinal;
    private Integer distanta;

    @Ignore
    private List<Punct> listaPuncte;

    public List<Punct> getListaPuncte() {
        return listaPuncte;
    }

    public void setListaPuncte(List<Punct> listaPuncte) {
        this.listaPuncte = listaPuncte;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public Date getDataStart() {
        return dataStart;
    }

    public void setDataStart(Date dataStart) {
        this.dataStart = dataStart;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Integer getDistanta() {
        return distanta;
    }

    public Integer getId() {
        return id;
    }

    public void setDistanta(Integer distanta) {
        this.distanta = distanta;
    }

    public Traseu(Integer id, String denumire, Date dataStart, Date dataFinal, Integer distanta, List<Punct> listaPuncte){
        this.id = id;
        this.denumire = denumire;
        this.dataStart = dataStart;
        this.dataFinal = dataFinal;
        this.distanta = distanta;
        this.listaPuncte = listaPuncte;
    }

    public Traseu() {

    }
}
