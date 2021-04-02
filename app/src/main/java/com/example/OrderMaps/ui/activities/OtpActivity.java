package com.example.OrderMaps.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.OrderMaps.model.apimodel.SignupResponse;
import com.example.OrderMaps.model.local.PreferencesManager;
import com.example.OrderMaps.viewmodel.LoginViewModel;
import com.example.OrderMaps.viewmodel.ResendOtpViewModel;
import com.example.OrderMaps.viewmodel.VerifiyViewModel;
import com.example.splashactivity.R;
import com.example.splashactivity.databinding.ActivityOtpBinding;


public class OtpActivity extends AppCompatActivity {
    ActivityOtpBinding binding;
    VerifiyViewModel viewModel;
    ResendOtpViewModel resendOtpViewModel;
    PreferencesManager preferencesManager;
    String otptxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_otp);
        viewModel= new ViewModelProvider(this).get(VerifiyViewModel.class);
       resendOtpViewModel= new ViewModelProvider(this).get(ResendOtpViewModel.class);

        preferencesManager=new PreferencesManager(this);
        observedata();
        obsrvedata2();
        binding.sendagaingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resendcode();

            }
        });
        binding.confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyuser();
            }
        });
    }
    public void observedata(){
        viewModel.signupResponseMutableLiveData.observe(this, new Observer<SignupResponse>() {
            @Override
            public void onChanged(SignupResponse signupResponse) {

                Intent intent=new Intent(OtpActivity.this,HomeActivity.class);
                startActivity(intent);

            }
        });
        viewModel.getMsgError().observe(this, s -> {
            Toast.makeText(this,"برجاء إدخال كود صحيح",Toast.LENGTH_LONG).show();
        });
        viewModel.getMsgRes().observe(this, res -> {
            Toast.makeText(this,"لا يوجد اتصال بالإنترنت",Toast.LENGTH_LONG).show();
        });

    }
    public void obsrvedata2(){
        resendOtpViewModel.signupResponseMutableLiveData.observe(this, new Observer<SignupResponse>() {
            @Override
            public void onChanged(SignupResponse signupResponse) {

            }
        });
    }
    public void verifyuser(){
        otptxt=binding.firstPinView.getText().toString().trim();
        if (otptxt.isEmpty()) {
            binding.firstPinView.setError("ادخل الكود");
            binding.firstPinView.requestFocus();
            return;
        }
        viewModel.getverifycode(otptxt,"Bearer " + preferencesManager.fetchtoken());



    }
    public void resendcode(){
        resendOtpViewModel.resendotp("Bearer " + preferencesManager.fetchtoken());
    }
}