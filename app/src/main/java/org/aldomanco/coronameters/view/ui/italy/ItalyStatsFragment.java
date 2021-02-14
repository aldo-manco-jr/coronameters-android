package org.aldomanco.coronameters.view.ui.italy;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.aldomanco.coronameters.R;
import org.aldomanco.coronameters.view.DailyItalyStatsActivity;
import org.aldomanco.coronameters.view.MapsActivity;
import org.aldomanco.coronameters.view.NavigationActivity;
import org.aldomanco.coronameters.view.OnBackPressed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class ItalyStatsFragment extends Fragment implements OnBackPressed, View.OnClickListener {

    private CalendarView calendarView;
    private TextView dateChosen;
    private Spinner spinnerChooseRegion;
    private Button buttonShowStats;

    private String dateChosenString;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        calendarView = view.findViewById(R.id.calendar);
        dateChosen = view.findViewById(R.id.text_mark_date);
        spinnerChooseRegion = view.findViewById(R.id.spinner_choose_region);
        buttonShowStats = view.findViewById(R.id.button_show_stats);

        dateChosenString = returnJSONFormattedDate();
        dateChosen.setText("Date Chosen: " + returnFormattedDate());

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                dateChosenString = returnJSONFormattedDate(i2, i1, i);
                dateChosen.setText("Date Chosen: " + returnFormattedDate(i2, i1, i));
            }
        });

        FloatingActionButton fabMaps = view.findViewById(R.id.fab_maps);
        fabMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Apri Mappa", Snackbar.LENGTH_LONG)
                        .setAction("Apri Mappa", null).show();

                Intent intent2 = new Intent(NavigationActivity.getNavigationActivity(), MapsActivity.class);
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
        ArrayAdapter<String> adapter = new ArrayAdapter<>(NavigationActivity.getNavigationActivity(), R.layout.style_text_spinner, regionsArrayList);
        spinnerChooseRegion.setAdapter(adapter);

        buttonShowStats.setOnClickListener(this);
    }

    public static ItalyStatsFragment newInstance() {
        ItalyStatsFragment fragment = new ItalyStatsFragment();
        return fragment;
    }

    private String returnFormattedDate(){

        Date today = new Date();
        StringBuilder stringBuilder = new StringBuilder();

        if (today.getDate()<10){
            stringBuilder.append("0" + today.getDate() + "/");
        }else {
            stringBuilder.append(today.getDate() + "/");
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

        if (today.getDate()<10){
            stringBuilder.append("0" + today.getDate() + "T17:00:00");
        }else {
            stringBuilder.append(today.getDate() + "T17:00:00");
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

            Intent intent = new Intent(NavigationActivity.getNavigationActivity(), DailyItalyStatsActivity.class);
            intent.putExtra("nome_regione", spinnerChooseRegion.getSelectedItem().toString());
            intent.putExtra("datetime", dateChosenString);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {

    }
}