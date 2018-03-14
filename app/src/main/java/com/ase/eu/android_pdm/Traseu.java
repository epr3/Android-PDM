package com.ase.eu.android_pdm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Traseu implements Serializable{

    private String denumire;
    private Date dataStart;
    private Date dataFinal;
    private Integer distanta;
    private ArrayList<Punct> listaPuncte;

    public ArrayList<Punct> getListaPuncte() {
        return listaPuncte;
    }

    public void setListaPuncte(ArrayList<Punct> listaPuncte) {
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

    public void setDistanta(Integer distanta) {
        this.distanta = distanta;
    }

    public Traseu(String denumire, Date dataStart, Date dataFinal, Integer distanta, ArrayList<Punct> listaPuncte){
        this.denumire = denumire;
        this.dataStart = dataStart;
        this.dataFinal = dataFinal;
        this.distanta = distanta;
        this.listaPuncte = listaPuncte;
    }

    public Traseu() {

    }
}
