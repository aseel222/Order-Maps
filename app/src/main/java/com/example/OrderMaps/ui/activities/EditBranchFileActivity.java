package com.example.OrderMaps.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.OrderMaps.model.apimodel.SignupResponse;
import com.example.OrderMaps.model.local.PreferencesManager;
import com.example.OrderMaps.viewmodel.CancelOrderViewModel;
import com.example.OrderMaps.viewmodel.ProfiledataViewModel;
import com.example.OrderMaps.viewmodel.UbdateViewModel;
import com.example.splashactivity.R;
import com.example.splashactivity.databinding.ActivityEditBranchFileBinding;

public class EditBranchFileActivity extends AppCompatActivity {
    ActivityEditBranchFileBinding binding;
    UbdateViewModel ubdateViewModel;
    PreferencesManager preferencesManager;
    ProfiledataViewModel profiledataViewModel;
    String username,email,phone,address;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_branch_file);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_edit_branch_file);
        ubdateViewModel= new ViewModelProvider(this).get(UbdateViewModel.class);
        preferencesManager=new PreferencesManager(this);
        profiledataViewModel=new ViewModelProvider(this).get(ProfiledataViewModel.class);
        profiledataViewModel.getprofiledata("Bearer "+preferencesManager.fetchtoken());
       observedata();
       obserevedata2();
        binding.ubdatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validatedata();
            }
        });


        binding.backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(EditBranchFileActivity.this, BranchFileActivity.class);
                startActivity(intent);
            }
        });

    }
    public void observedata(){
        ubdateViewModel.signupResponseMutableLiveData.observe(this, new Observer<SignupResponse>() {
            @Override
            public void onChanged(SignupResponse signupResponse) {
                Intent intent=new Intent(EditBranchFileActivity.this,BranchFileActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
    public void validatedata(){
        username=binding.nameEtxt.getText().toString().trim();
        email=binding.emailEtxt.getText().toString().trim();
        phone=binding.phoneEtxt.getText().toString().trim();
        address=binding.addressEtxt.getText().toString().trim();
        ubdateViewModel.ubdateprofile(username,email,phone,address,"Bearer "+preferencesManager.fetchtoken());


    }
    public void obserevedata2(){
        profiledataViewModel.profiledatamutablelivedata.observe(this, new Observer<SignupResponse>() {
            @Override
            public void onChanged(SignupResponse signupResponse) {
                binding.nameEtxt.setText(signupResponse.getData().getUsername());
                binding.emailEtxt.setText(signupResponse.getData().getEmail());
                binding.phoneEtxt.setText(signupResponse.getData().getPhone()+"");
                binding.addressEtxt.setText(signupResponse.getData().getAddress());

            }
        });
    }

}