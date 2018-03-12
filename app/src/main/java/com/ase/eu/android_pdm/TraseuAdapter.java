package com.ase.eu.android_pdm;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

class TraseuAdapter extends RecyclerView.Adapter<TraseuAdapter.ViewHolder> {
    private ArrayList<Traseu> lista;

    public TraseuAdapter(ArrayList<Traseu> lista) {
        this.lista = lista;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView denumire, dataStart, dataFinal, distanta;
        public ViewHolder(final View v) {
            super(v);
            denumire = (TextView) v.findViewById(R.id.denumire);
            dataStart = (TextView) v.findViewById(R.id.dataStart);
            dataFinal = (TextView) v.findViewById(R.id.dataFinal);
            distanta = (TextView) v.findViewById(R.id.distanta);
        }
    }



    @Override
    public TraseuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.traseu_list_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TraseuAdapter.ViewHolder holder, int position) {
        Traseu traseu = lista.get(position);
        holder.denumire.setText(traseu.getDenumire());
        holder.dataFinal.setText(traseu.getDataFinal().toString());
        holder.dataStart.setText(traseu.getDataStart().toString());
        holder.distanta.setText(traseu.getDistanta().toString());
    }

    @Override
    public int getItemCount() {
        return (lista != null ? lista.size(): 0);
    }
}
