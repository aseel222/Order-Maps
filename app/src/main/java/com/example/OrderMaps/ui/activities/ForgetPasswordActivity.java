package com.example.OrderMaps.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.splashactivity.R;
import com.example.splashactivity.databinding.ActivityForgetPasswordBinding;


public class ForgetPasswordActivity extends AppCompatActivity {
ActivityForgetPasswordBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_forget_password);
        binding.nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ForgetPasswordActivity.this, ResetPasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}