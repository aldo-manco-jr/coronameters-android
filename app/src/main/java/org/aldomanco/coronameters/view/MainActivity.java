package org.aldomanco.coronameters.view;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.aldomanco.coronameters.R;
import org.aldomanco.coronameters.model.DailyRegionStats;
import org.aldomanco.coronameters.viewmodel.StatsViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CalendarView calendarView;
    private TextView dateChosen;
    private Spinner spinnerChooseRegion;
    private Button buttonShowStats;

    private String dateChosenString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        calendarView = findViewById(R.id.calendar);
        dateChosen = findViewById(R.id.text_mark_date);
        spinnerChooseRegion = findViewById(R.id.spinner_choose_region);
        buttonShowStats = findViewById(R.id.button_show_stats);

        dateChosenString = returnJSONFormattedDate();
        dateChosen.setText("Date Chosen: " + returnFormattedDate());

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                dateChosenString = returnJSONFormattedDate(i2, i1, i);
                dateChosen.setText("Date Chosen: " + returnFormattedDate(i2, i1, i));
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Invia Segnalazione alla Protezione Civile", Snackbar.LENGTH_LONG)
                        .setAction("Invia Segnalazione alla Protezione Civile", null).show();

                Intent intent = new Intent(getApplicationContext(), SendMailActivity.class);
                startActivity(intent);
            }
        });

        FloatingActionButton fabMaps = findViewById(R.id.fab_maps);
        fabMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Apri Mappa", Snackbar.LENGTH_LONG)
                        .setAction("Apri Mappa", null).show();

                Intent intent2 = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent2);
            }
        });

        String[] regionsList = {
                "Abruzzo",
                "Basilicata",
                "Calabria",
                "Campania",
                "Emilia-Romagna",
                "Friuli Venezia Giulia",
                "Lazio",
                "Liguria",
                "Lombardia",
                "Marche",
                "Molise",
                "P.A. Bolzano",
                "P.A. Trento",
                "Piemonte",
                "Puglia",
                "Sardegna",
                "Sicilia",
                "Toscana",
                "Umbria",
                "Valle d'Aosta",
                "Veneto"
        };

        ArrayList<String> regionsArrayList = new ArrayList<>(Arrays.asList(regionsList));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.style_text_spinner, regionsArrayList);
        spinnerChooseRegion.setAdapter(adapter);

        buttonShowStats.setOnClickListener(this);
    }

    private String returnFormattedDate(){

        Date today = new Date();
        StringBuilder stringBuilder = new StringBuilder();

        if (today.getDay()<10){
            stringBuilder.append("0" + today.getDay() + "/");
        }else {
            stringBuilder.append(today.getDay() + "/");
        }

        if (today.getMonth()<10){
            stringBuilder.append("0" + (today.getMonth()+1) + "/");
        }else {
            stringBuilder.append((today.getMonth()+1) + "/");
        }

        stringBuilder.append((today.getYear() + 1900));
        return stringBuilder.toString();
    }

    private String returnJSONFormattedDate(){

        Date today = new Date();
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append((today.getYear() + 1900) + "-");

        if (today.getMonth()<10){
            stringBuilder.append("0" + (today.getMonth()+1) + "-");
        }else {
            stringBuilder.append((today.getMonth()+1) + "-");
        }

        if (today.getDay()<10){
            stringBuilder.append("0" + today.getDay() + "T17:00:00");
        }else {
            stringBuilder.append(today.getDay() + "T17:00:00");
        }

        return stringBuilder.toString();
    }

    private String returnFormattedDate(int day, int month, int year){

        StringBuilder stringBuilder = new StringBuilder();

        if (day<10){
            stringBuilder.append("0" + day + "/");
        }else {
            stringBuilder.append(day + "/");
        }

        if (month<10){
            stringBuilder.append("0" + (month+1) + "/");
        }else {
            stringBuilder.append((month+1) + "/");
        }

        stringBuilder.append(year);
        return stringBuilder.toString();
    }

    private String returnJSONFormattedDate(int day, int month, int year){

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(year + "-");

        if (month<10){
            stringBuilder.append("0" + (month+1) + "-");
        }else {
            stringBuilder.append((month+1) + "-");
        }

        if (day<10){
            stringBuilder.append("0" + day + "T17:00:00");
        }else {
            stringBuilder.append(day + "T17:00:00");
        }

        return stringBuilder.toString();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_show_stats) {
            //Toast.makeText(this, dateChosenString + "\n" + spinnerChooseRegion.getSelectedItem().toString(), Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, DailyRegionStatsActivity.class);
            intent.putExtra("nome_regione", spinnerChooseRegion.getSelectedItem().toString());
            intent.putExtra("datetime", dateChosenString);
            startActivity(intent);
        }
    }
}