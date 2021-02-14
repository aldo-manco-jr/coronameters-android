package org.aldomanco.coronameters.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "daily_region_stats")
public class DailyRegionStats {

    @PrimaryKey(autoGenerate = true)
    private int id;

    // alternative keys
    private String nome_regione;
    private String datetime;

    private int ricoverati_con_sintomi;
    private int terapia_intensiva;
    private int persone_ospedalizzate;
    private int persone_isolate_domicilio;
    private int totale_positivi;
    private int nuovi_positivi;
    private int persone_guarite;
    private int persone_decedute;
    private int totale_casi;
    private int numero_tamponi;

    public DailyRegionStats(String nome_regione, String datetime, int ricoverati_con_sintomi, int terapia_intensiva, int persone_ospedalizzate, int persone_isolate_domicilio, int totale_positivi, int nuovi_positivi, int persone_guarite, int persone_decedute, int totale_casi, int numero_tamponi) {
        this.nome_regione = nome_regione;
        this.datetime = datetime;

        this.ricoverati_con_sintomi = ricoverati_con_sintomi;
        this.terapia_intensiva = terapia_intensiva;
        this.persone_ospedalizzate = persone_ospedalizzate;
        this.persone_isolate_domicilio = persone_isolate_domicilio;
        this.totale_positivi = totale_positivi;
        this.nuovi_positivi = nuovi_positivi;
        this.persone_guarite = persone_guarite;
        this.persone_decedute = persone_decedute;
        this.totale_casi = totale_casi;
        this.numero_tamponi = numero_tamponi;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNome_regione() {
        return nome_regione;
    }

    public String getDatetime() {
        return datetime;
    }

    public int getRicoverati_con_sintomi() {
        return ricoverati_con_sintomi;
    }

    public int getTerapia_intensiva() {
        return terapia_intensiva;
    }

    public int getPersone_ospedalizzate() {
        return persone_ospedalizzate;
    }

    public int getPersone_isolate_domicilio() {
        return persone_isolate_domicilio;
    }

    public int getTotale_positivi() {
        return totale_positivi;
    }

    public int getNuovi_positivi() {
        return nuovi_positivi;
    }

    public int getPersone_guarite() {
        return persone_guarite;
    }

    public int getPersone_decedute() {
        return persone_decedute;
    }

    public int getTotale_casi() {
        return totale_casi;
    }

    public int getNumero_tamponi() {
        return numero_tamponi;
    }
}