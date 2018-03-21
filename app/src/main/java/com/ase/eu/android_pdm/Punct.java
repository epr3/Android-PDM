package com.ase.eu.android_pdm;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "punct", foreignKeys = @ForeignKey(entity = Traseu.class,
                                                        parentColumns = "id",
                                                        childColumns = "traseuId",
                                                        onDelete = CASCADE))
public class Punct implements Serializable{
    public int getId() {
        return id;
    }

    public int getTraseuId() {
        return traseuId;
    }

    @PrimaryKey(autoGenerate = true) private int id;
    double longitude;
    double latitude;
    private int traseuId;

    public void setId(int id) {
        this.id = id;
    }

    public void setTraseuId(int traseuId) {
        this.traseuId = traseuId;
    }

    public Punct(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Punct(){

    }

    @Override
    public String toString() {
        return "Punct: " + latitude + ", " + longitude;
    }
}
