package org.aldomanco.coronameters.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.sqlite.db.SimpleSQLiteQuery;

import org.aldomanco.coronameters.model.DailyRegionStats;

import java.util.ArrayList;
import java.util.List;

public class StatsRepository {

    private StatsProvider statsProvider;
    private LiveData<List<DailyRegionStats>> listDailyRegionStats;
    private List<DailyRegionStats> selectedStats;

    public StatsRepository(Application application, String nomeRegione, String datetime) {
        StatsDatabase statsDatabase = StatsDatabase.getInstance(application);
        statsProvider = statsDatabase.statsProvider();

        /*// Query string
        String queryString = new String();
        List<Object> args = new ArrayList();

        queryString += "SELECT * FROM daily_region_stats WHERE nome_regione LIKE ? AND datetime LIKE ?";
        args.add(nomeRegione);
        args.add(datetime);

        SimpleSQLiteQuery query = new SimpleSQLiteQuery(queryString, args.toArray());*/

        listDailyRegionStats = statsProvider.getAllDailyRegionStats();
    }

    public void insert(DailyRegionStats dailyRegionStats) {
        new InsertStatsAsyncTask(statsProvider).execute(dailyRegionStats);
    }

    public void delete(DailyRegionStats dailyRegionStats) {
        new DeleteStatsAsyncTask(statsProvider).execute(dailyRegionStats);
    }

    public LiveData<List<DailyRegionStats>> getListDailyRegionStats() {
        return listDailyRegionStats;
    }

    public List<DailyRegionStats> getSelectedStats() {
        return selectedStats;
    }

    private static class InsertStatsAsyncTask extends AsyncTask<DailyRegionStats, Void, Void>{

        private StatsProvider statsProvider;

        private InsertStatsAsyncTask(StatsProvider statsProvider){
            this.statsProvider = statsProvider;
        }

        @Override
        protected Void doInBackground(DailyRegionStats... dailyRegionStats) {
            statsProvider.insert(dailyRegionStats[0]);
            return null;
        }
    }

    private static class DeleteStatsAsyncTask extends AsyncTask<DailyRegionStats, Void, Void>{

        private StatsProvider statsProvider;

        private DeleteStatsAsyncTask(StatsProvider statsProvider){
            this.statsProvider = statsProvider;
        }

        @Override
        protected Void doInBackground(DailyRegionStats... dailyRegionStats) {
            statsProvider.delete(dailyRegionStats[0]);
            return null;
        }
    }
}
