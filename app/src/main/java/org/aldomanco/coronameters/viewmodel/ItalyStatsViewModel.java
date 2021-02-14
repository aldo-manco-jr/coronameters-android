package org.aldomanco.coronameters.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.aldomanco.coronameters.database.StatsRepository;
import org.aldomanco.coronameters.model.DailyRegionStats;

import java.util.List;

public class ItalyStatsViewModel extends AndroidViewModel {

    private StatsRepository statsRepository;
    private LiveData<List<DailyRegionStats>> listDailyRegionStats;

    public ItalyStatsViewModel(@NonNull Application application, String nomeRegione, String datetime){
        super(application);
        statsRepository = new StatsRepository(application, nomeRegione, datetime);
        listDailyRegionStats = statsRepository.getListDailyRegionStats();
    }

    public void insert(DailyRegionStats dailyRegionStats){
        statsRepository.insert(dailyRegionStats);
    }

    public void delete(DailyRegionStats dailyRegionStats){
        statsRepository.delete(dailyRegionStats);
    }

    public LiveData<List<DailyRegionStats>> getListDailyRegionStats(){
        return listDailyRegionStats;
    }
}
