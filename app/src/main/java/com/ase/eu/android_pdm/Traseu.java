package com.ase.eu.android_pdm;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

public class Traseu implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Traseu createFromParcel(Parcel in) {
            return new Traseu(in);
        }

        public Traseu[] newArray(int size) {
            return new Traseu[size];
        }
    };

    public Traseu(Parcel in){
        this.denumire = in.readString();
        this.dataStart =  (Date) in.readSerializable();
        this.dataFinal =  (Date) in.readSerializable();
        this.distanta = in.readInt();
        this.listaPuncte = (ArrayList<Punct>)in.readSerializable();
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(denumire);
        dest.writeSerializable(dataStart);
        dest.writeSerializable(dataFinal);
        dest.writeInt(distanta);
        dest.writeSerializable(listaPuncte);
    }

}
