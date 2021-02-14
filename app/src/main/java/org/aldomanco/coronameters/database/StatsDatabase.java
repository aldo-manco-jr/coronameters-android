package org.aldomanco.coronameters.database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import org.aldomanco.coronameters.R;
import org.aldomanco.coronameters.model.DailyRegionStats;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Database(entities = {DailyRegionStats.class}, version = 1, exportSchema = true)
public abstract class StatsDatabase extends RoomDatabase {

    private static StatsDatabase database;

    private static Context activity;

    public abstract StatsProvider statsProvider();

    public static synchronized StatsDatabase getInstance(Context context) {

        activity = context.getApplicationContext();

        if (database == null) {
            database = Room.databaseBuilder(
                    context.getApplicationContext(),
                    StatsDatabase.class,
                    "stats_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }

        return database;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDatabaseAsyncTask(database).execute();
        }
    };

    private static class PopulateDatabaseAsyncTask extends AsyncTask<Void, Void, Void>{

        private StatsProvider statsProvider;

        private PopulateDatabaseAsyncTask(StatsDatabase statsDatabase){
            statsProvider = statsDatabase.statsProvider();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //statsProvider.insert(new DailyRegionStats("Abruzzo",  "2020-02-24T18:00:00", 0,0,0,0,0,0,0,0,0));
            //statsProvider.insert(new DailyRegionStats("Molise",  "2020-02-24T18:00:00", 0,0,0,0,0,0,0,0,0));
            Log.i("WLO", "WLO");

            fillWithStartingData(activity);
            return null;
        }
    }

    private static void fillWithStartingData(Context context){
        StatsProvider statsProvider = getInstance(context).statsProvider();

        JSONArray listDailyRegionStats = loadJSONArray(context);

        try{
            for (int i = 0; i < listDailyRegionStats.length(); i++) {
                JSONObject dailyRegionStats = listDailyRegionStats.getJSONObject(i);

                String nomeRegione = dailyRegionStats.getString("denominazione_regione");
                String datetime = dailyRegionStats.getString("data");

                int totaleOspedalizzati = dailyRegionStats.getInt("totale_ospedalizzati");
                int terapiaIntensiva = dailyRegionStats.getInt("terapia_intensiva");
                int ricoveratiConSintomi = dailyRegionStats.getInt("ricoverati_con_sintomi");
                int personeIsolateDomicilio = dailyRegionStats.getInt("isolamento_domiciliare");
                int totalePositivi = dailyRegionStats.getInt("totale_positivi");
                int totaleCasi = dailyRegionStats.getInt("totale_casi");
                int nuoviPositivi = dailyRegionStats.getInt("nuovi_positivi");
                int personeGuarite = dailyRegionStats.getInt("dimessi_guariti");
                int numeroTamponi = dailyRegionStats.getInt("tamponi");
                int deceduti = dailyRegionStats.getInt("deceduti");

                statsProvider.insert(
                        new DailyRegionStats(
                                nomeRegione,
                                datetime,
                                ricoveratiConSintomi,
                                terapiaIntensiva,
                                totaleOspedalizzati,
                                personeIsolateDomicilio,
                                totalePositivi,
                                nuoviPositivi,
                                personeGuarite,
                                deceduti,
                                totaleCasi,
                                numeroTamponi));
            }
        }catch (JSONException ignored){}
    }

    private static JSONArray loadJSONArray(Context context){

        StringBuilder stringBuilder = new StringBuilder();
        InputStream inputStream = context.getResources().openRawResource(R.raw.xab);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;

        try {
            while ((line = reader.readLine()) != null){
                stringBuilder.append(line);
            }

            JSONObject jsonObject = new JSONObject(stringBuilder.toString());

            return jsonObject.getJSONArray("stats");

        }catch (IOException | JSONException exception){
            exception.printStackTrace();
        }
        return null;
    }
}
