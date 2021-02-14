package org.aldomanco.coronameters.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import org.aldomanco.coronameters.R;
import org.aldomanco.coronameters.model.DailyRegionStats;
import org.aldomanco.coronameters.viewmodel.StatsViewModel;
import org.aldomanco.coronameters.viewmodel.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class DailyRegionStatsActivity extends AppCompatActivity {

    private StatsViewModel statsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_region_stats);

        RecyclerView recyclerView = findViewById(R.id.recyclerview_stats);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final DailyRegionStatsAdapter adapter = new DailyRegionStatsAdapter();
        recyclerView.setAdapter(adapter);

        String nomeRegione = getIntent().getExtras().getString("nome_regione");
        String datetime = getIntent().getExtras().getString("datetime");

        statsViewModel = ViewModelProviders.of(this, new ViewModelFactory(getApplication(), nomeRegione, datetime)).get(StatsViewModel.class);

        statsViewModel.getListDailyRegionStats().observe(this, new Observer<List<DailyRegionStats>>() {
            @Override
            public void onChanged(List<DailyRegionStats> dailyRegionStats) {

                List<DailyRegionStats> selectedStats = new ArrayList<>();
                for (DailyRegionStats tmp : dailyRegionStats) {
                    if (tmp.getNome_regione().equals(nomeRegione) && tmp.getDatetime().equals(datetime)){
                        selectedStats.add(tmp);
                    }
                }

                adapter.setListDailyRegionStats(selectedStats);
            }
        });
    }
}