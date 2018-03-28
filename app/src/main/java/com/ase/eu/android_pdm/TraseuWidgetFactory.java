package com.ase.eu.android_pdm;


import android.content.Context;
import android.database.Cursor;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.List;

public class TraseuWidgetFactory implements RemoteViewsService.RemoteViewsFactory{
    private List<Traseu> listaTrasee;
    private Context context;

    public TraseuWidgetFactory(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

       // Cursor cursor = context.getContentResolver().query(TraseuContentProvider.CONTENT_URI, null, null, null, null);

        Cursor cursor = TraseuDatabase.getInstance(context).getTraseuDAO().selectCursorTrasee();
        listaTrasee = new ArrayList<>();

        while (cursor.moveToNext()) {
            Traseu traseu = new Traseu();
            traseu.setDenumire(cursor.getString(1));
            traseu.setId(cursor.getInt(0));

            Punct punct1 = new Punct(40, 45);
            Punct punct2 = new Punct(50, 60);
            ArrayList<Punct> listaPuncte = new ArrayList<>();
            listaPuncte.add(punct1);
            listaPuncte.add(punct2);
            traseu.setListaPuncte(listaPuncte);

            listaTrasee.add(traseu);
        }
        cursor.close();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return listaTrasee.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.traseu_widget_list_row);
        rv.setTextViewText(R.id.wd_denumire, listaTrasee.get(position).getDenumire());
        //rv.setTextViewText(R.id.wd_data, listaTrasee.get(position).getDataStart().toString());
        rv.setTextViewText(R.id.wd_suprafata, listaTrasee.get(position).getListaPuncte().size() + "puncte");

        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
