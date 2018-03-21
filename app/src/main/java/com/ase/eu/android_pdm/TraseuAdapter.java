package com.ase.eu.android_pdm;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class TraseuAdapter extends RecyclerView.Adapter<TraseuAdapter.ViewHolder> {
    private List<TraseuPuncte> lista;

    public TraseuAdapter(List<TraseuPuncte> lista) {
        this.lista = lista;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView denumire, dataStart, dataFinal, distanta;
        public ViewHolder(final View v) {
            super(v);
            denumire = v.findViewById(R.id.denumire);
            dataStart = v.findViewById(R.id.dataStart);
            dataFinal = v.findViewById(R.id.dataFinal);
            distanta = v.findViewById(R.id.distanta);
        }
    }



    @Override
    public TraseuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.traseu_list_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TraseuAdapter.ViewHolder holder, int position) {
        TraseuPuncte traseu = lista.get(position);
        holder.denumire.setText(traseu.getTraseu().getDenumire());
        holder.dataFinal.setText(traseu.getTraseu().getDataFinal().toString());
        holder.dataStart.setText(traseu.getTraseu().getDataStart().toString());
        holder.distanta.setText((traseu.getListaPuncte() != null
                ? traseu.getListaPuncte().size()
                : 0) + " puncte");
    }

    @Override
    public int getItemCount() {
        return (lista != null ? lista.size(): 0);
    }
}
