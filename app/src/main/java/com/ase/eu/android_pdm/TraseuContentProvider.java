package com.ase.eu.android_pdm;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class TraseuContentProvider extends ContentProvider {
    public static final String AUTHORITY = "com.ase.eu.android_pdm";

    public static final Uri CONTENT_URI= Uri.parse("content://"+AUTHORITY+"/traseu");

    @Override
    public boolean onCreate() {
        return false;
    }



    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        //if(uri.equals(Uri.parse("TRASEU_URI"))) {
            return TraseuDatabase.getInstance(getContext()).getTraseuDAO().selectCursorTrasee();
        //} else {
            //return null;
        //}
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }



    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
