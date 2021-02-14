package org.aldomanco.coronameters.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.sqlite.db.SupportSQLiteQuery;

import org.aldomanco.coronameters.model.DailyRegionStats;

import java.util.List;

@Dao
public interface StatsProvider {

    @Insert
    void insert(DailyRegionStats dailyRegionStats);

    @Delete
    void delete(DailyRegionStats dailyRegionStats);

    @Query("SELECT * FROM daily_region_stats ORDER BY nome_regione ASC")
    LiveData<List<DailyRegionStats>> getAllDailyRegionStats();

    /*@RawQuery
    LiveData<List<DailyRegionStats>> getSelectedStats(SupportSQLiteQuery query);*/
}
