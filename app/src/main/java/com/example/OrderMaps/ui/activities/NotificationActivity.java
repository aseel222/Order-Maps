package com.example.OrderMaps.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.OrderMaps.model.apimodel.DatumNotification;
import com.example.OrderMaps.model.apimodel.NotificationResponse;
import com.example.OrderMaps.model.local.PreferencesManager;
import com.example.OrderMaps.ui.adapter.NotificationAdapter;
import com.example.OrderMaps.viewmodel.NotificationViewModel;
import com.example.splashactivity.R;
import com.example.splashactivity.databinding.ActivityNotificationBinding;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {
    ActivityNotificationBinding binding;
    NotificationAdapter adapter;
    ArrayList<DatumNotification>data;
    NotificationViewModel notificationViewModel;
    PreferencesManager preferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_notification);
        preferencesManager=new PreferencesManager(this);
        notificationViewModel=new ViewModelProvider(this).get(NotificationViewModel.class);
        notificationViewModel.getnotifications("Bearer "+preferencesManager.fetchtoken());
        observedata();
        setupAdapter();
        binding.backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBackIntent();
                finish();
            }
        });



    }
    public void sendBackIntent() {
        Intent intent = new Intent();
        intent.putExtra("id", 12); // for test
        setResult(RESULT_OK, intent);
    }
    private void setupAdapter () {
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        binding.notificationrecycler.setLayoutManager(linearLayout);
        adapter = new NotificationAdapter(new ArrayList<DatumNotification>(),this);
        binding.notificationrecycler.setAdapter(adapter);

    }
    public void observedata(){
        notificationViewModel.notificationResponseMutableLiveData.observe(this, new Observer<NotificationResponse>() {
            @Override
            public void onChanged(NotificationResponse notificationResponse) {
                adapter.setlist(notificationResponse.getData());
                notificationViewModel.getnotifications("Bearer "+preferencesManager.fetchtoken());



            }
        });
    }
}