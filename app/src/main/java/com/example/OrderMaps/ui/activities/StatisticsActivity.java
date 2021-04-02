package com.example.OrderMaps.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import com.example.OrderMaps.model.apimodel.Datum;
import com.example.OrderMaps.model.apimodel.StatisticsDatum;
import com.example.OrderMaps.model.apimodel.StatisticsResponse;
import com.example.OrderMaps.model.local.PreferencesManager;
import com.example.OrderMaps.ui.adapter.Order2Adapter;
import com.example.OrderMaps.ui.adapter.StatisticsAdapter;
import com.example.OrderMaps.model.StatisticsModel;
import com.example.OrderMaps.viewmodel.StatisticsViewModel;
import com.example.splashactivity.R;
import com.example.splashactivity.databinding.ActivityStatisticsBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class StatisticsActivity extends AppCompatActivity {
    ArrayList<StatisticsDatum> data;
ActivityStatisticsBinding binding;
    StatisticsAdapter adapter;
    Toolbar toolbar;

    PreferencesManager preferencesManager;
    StatisticsViewModel statisticsViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_statistics);
        statisticsViewModel=new ViewModelProvider(this).get(StatisticsViewModel.class);
        preferencesManager=new PreferencesManager(this);
        statisticsViewModel.getstastics("Bearer " + preferencesManager.fetchtoken());
        observedata();
        setupAdapter();



        binding.backicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBackIntent();
                finish();

            }
        });


    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.home,menu);
        MenuItem searchViewItem = menu.findItem(R.id.app_bar_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);


        return true;

    }*/


    public void sendBackIntent() {
        Intent intent = new Intent();
        intent.putExtra("id", 12); // for test
        setResult(RESULT_OK, intent);
    }
    private void setupAdapter() {
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        binding.statistcsRecyclerview.setLayoutManager(linearLayout);
        adapter = new StatisticsAdapter(new ArrayList<StatisticsDatum>(), this);
        binding.statistcsRecyclerview.setAdapter(adapter);

    }
    public void observedata(){
        statisticsViewModel.statisticsResponseMutableLiveData.observe(this, new Observer<StatisticsResponse>() {
            @Override
            public void onChanged(StatisticsResponse statisticsResponse) {
                adapter.setlist(statisticsResponse.getData());
                statisticsViewModel.getstastics("Bearer " + preferencesManager.fetchtoken());
            }
        });

    }

}