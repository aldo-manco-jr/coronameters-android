package org.aldomanco.coronameters.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.aldomanco.coronameters.R;
import org.aldomanco.coronameters.model.DailyRegionStats;

import java.util.ArrayList;
import java.util.List;

public class DailyRegionStatsAdapter extends RecyclerView.Adapter<DailyRegionStatsAdapter.DailyRegionStatsHolder> {

    private List<DailyRegionStats> listDailyRegionStats = new ArrayList<>();

    @NonNull
    @Override
    public DailyRegionStatsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_daily_region_stats, parent, false);
        return new DailyRegionStatsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DailyRegionStatsHolder holder, int position) {

        DailyRegionStats currentDailyRegionStats = listDailyRegionStats.get(position);

        holder.nome_regione.setText(currentDailyRegionStats.getNome_regione());
        holder.datetime.setText("Data: " + currentDailyRegionStats.getDatetime().split("T")[0]);

        holder.ricoverati_con_sintomi.setText("Ricoverati con Sintomi: " + currentDailyRegionStats.getRicoverati_con_sintomi());
        holder.terapia_intensiva.setText("Terapia Intensiva: " + currentDailyRegionStats.getTerapia_intensiva());
        holder.totale_ospedalizzati.setText("Totale Ospedalizzati: " + currentDailyRegionStats.getPersone_ospedalizzate());
        holder.isolamento_domiciliare.setText("Isolamento Domiciliare: " + currentDailyRegionStats.getPersone_isolate_domicilio());
        holder.totale_positivi.setText("Totale Positivi: " + currentDailyRegionStats.getTotale_positivi());
        holder.totale_casi.setText("Totale Casi: " + currentDailyRegionStats.getTotale_casi());
        holder.nuovi_positivi.setText("Nuovi Positivi: " + currentDailyRegionStats.getNuovi_positivi());
        holder.tamponi.setText("Tamponi: " + currentDailyRegionStats.getNumero_tamponi());
        holder.deceduti.setText("Deceduti: " + currentDailyRegionStats.getPersone_decedute());
        holder.dimessi_guariti.setText("Dimessi Guariti: " + currentDailyRegionStats.getPersone_guarite());
    }

    @Override
    public int getItemCount() {
        return listDailyRegionStats.size();
    }

    public void setListDailyRegionStats(List<DailyRegionStats> listDailyRegionStats){
        this.listDailyRegionStats = listDailyRegionStats;
        notifyDataSetChanged();
    }

    public int getListSize(){
        return listDailyRegionStats.size();
    }

    class DailyRegionStatsHolder extends RecyclerView.ViewHolder{

        private TextView nome_regione;
        private TextView datetime;

        private TextView ricoverati_con_sintomi;
        private TextView terapia_intensiva;
        private TextView totale_ospedalizzati;
        private TextView isolamento_domiciliare;
        private TextView totale_positivi;
        private TextView nuovi_positivi;
        private TextView dimessi_guariti;
        private TextView deceduti;
        private TextView totale_casi;
        private TextView tamponi;

        public DailyRegionStatsHolder(@NonNull View itemView) {
            super(itemView);
            this.nome_regione = itemView.findViewById(R.id.nome_regione);
            this.datetime = itemView.findViewById(R.id.datetime);

            this.ricoverati_con_sintomi = itemView.findViewById(R.id.ricoverati_con_sintomi);
            this.terapia_intensiva = itemView.findViewById(R.id.terapia_intensiva);
            this.totale_ospedalizzati = itemView.findViewById(R.id.totale_ospedalizzati);
            this.isolamento_domiciliare = itemView.findViewById(R.id.isolamento_domiciliare);
            this.totale_positivi = itemView.findViewById(R.id.totale_positivi);
            this.nuovi_positivi = itemView.findViewById(R.id.nuovi_positivi);
            this.dimessi_guariti = itemView.findViewById(R.id.totale_guariti);
            this.deceduti = itemView.findViewById(R.id.deceduti);
            this.totale_casi = itemView.findViewById(R.id.totale_casi);
            this.tamponi = itemView.findViewById(R.id.tamponi);
        }
    }
}
