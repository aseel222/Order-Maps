package com.example.OrderMaps.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.OrderMaps.model.apimodel.LogOutResponse;
import com.example.OrderMaps.model.apimodel.SignupResponse;
import com.example.OrderMaps.model.local.PreferencesManager;
import com.example.OrderMaps.viewmodel.LogoutViewModel;
import com.example.OrderMaps.viewmodel.ProfiledataViewModel;
import com.example.splashactivity.R;
import com.example.splashactivity.databinding.ActivityBranchFileBinding;

public class BranchFileActivity extends AppCompatActivity {
    ActivityBranchFileBinding binding;
    PreferencesManager preferencesManager;
    ProfiledataViewModel profiledataViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_file);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_branch_file);
        preferencesManager=new PreferencesManager(this);
        profiledataViewModel=new ViewModelProvider(this).get(ProfiledataViewModel.class);

        profiledataViewModel.getprofiledata("Bearer "+preferencesManager.fetchtoken());
        
        observedata();



        binding.backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BranchFileActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        binding.ediitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BranchFileActivity.this, EditBranchFileActivity.class);
                startActivity(intent);
            }
        });
    }
    public void observedata(){
        profiledataViewModel.profiledatamutablelivedata.observe(this, new Observer<SignupResponse>() {
            @Override
            public void onChanged(SignupResponse signupResponse) {
               binding.nametxt.setText(signupResponse.getData().getUsername());
                binding.emailtxt.setText(signupResponse.getData().getEmail());
                binding.phonenum.setText(signupResponse.getData().getPhone()+"");
                binding.addresstxt.setText(signupResponse.getData().getAddress());



            }
        });

    }

    }
