package com.ase.eu.android_pdm;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

@Database(entities = {Punct.class, Traseu.class}, version = 1)
@TypeConverters({TraseuDateConverter.class})
public abstract class TraseuDatabase extends RoomDatabase {
    private static TraseuDatabase db;

    public abstract TraseuDAO getTraseuDAO();
    public abstract PunctDAO getPunctDAO();

    public static TraseuDatabase getInstance(Context context) {
        if(db == null) {
            db =  Room.databaseBuilder(context,
                    TraseuDatabase.class, "trasee.db")
                    .allowMainThreadQueries()//nerecomandat
                    .build();
        }

        return db;
    }
}
