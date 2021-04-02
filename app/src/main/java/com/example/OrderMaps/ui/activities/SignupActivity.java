package com.example.OrderMaps.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.OrderMaps.model.apimodel.SignupResponse;
import com.example.OrderMaps.model.local.PreferencesManager;
import com.example.OrderMaps.viewmodel.SignupViewModel;
import com.example.splashactivity.R;
import com.example.splashactivity.databinding.ActivitySignupBinding;

public class SignupActivity extends AppCompatActivity {
    ActivitySignupBinding binding;
    String usernametxt,email,address,password,confirmpass,phone;
    SignupViewModel model;
    PreferencesManager sharedpref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_signup);
        model = new ViewModelProvider(this).get(SignupViewModel.class);
        sharedpref=new PreferencesManager(SignupActivity.this);
        observedata();
        binding.signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validationdata();
            }
        });



    }
    public void observedata(){
        model.signupResponseMutableLiveData.observe(this, new Observer<SignupResponse>() {
            @Override
            public void onChanged(SignupResponse signupResponse) {

                    sharedpref.savetoken(signupResponse.getData().getAccessToken());
                    Intent intent=new Intent(SignupActivity.this,OtpActivity.class);
                    startActivity(intent);
                    Toast.makeText(SignupActivity.this,"تم إنشاء حساب",Toast.LENGTH_LONG).show();
                    finish();














            }
        });
        model.getMsgError().observe(this, s -> {

          Toast.makeText(this,s,Toast.LENGTH_LONG).show();

        });
        model.isLoading().observe(this,isloading -> {
            if (isloading) {
                binding.loading.setVisibility(View.VISIBLE);
            } else {
                binding.loading.setVisibility(View.GONE);}

            });
        model.getMsgRes().observe(this, res -> {
            Toast.makeText(this,"لايوجد إتصال بالإنترنت",Toast.LENGTH_LONG).show();
        });

    }
    public void validationdata(){
        usernametxt=binding.nameEtxt.getText().toString().trim();
        email=binding.emailEtxt.getText().toString().trim();
        address=binding.addressEtxt.getText().toString().trim();
        password=binding.passEtxt.getText().toString().trim();
        confirmpass=binding.confirmpassEtxt.getText().toString().trim();
        phone=binding.phoneEtxt.getText().toString().trim();
        if (email.isEmpty()) {
            binding.emailEtxt.setError("يجب ادخال إلكتروني صحيح");
            binding.emailEtxt.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailEtxt.setError("يجب ادخال بريد إلكتروني صحيح");
            binding.emailEtxt.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            binding.passEtxt.setError("ادخل كلمه مرور صحيحه");
            binding.passEtxt.requestFocus();
            return;
        }
        if (password.length() < 6) {
            binding.passEtxt.setError("كلمه المرور يجب ان تكون اكبر من 5");
            binding.passEtxt.requestFocus();
            return;
        }
        if (confirmpass.isEmpty()) {
            binding.confirmpassEtxt.setError("ادخل كلمه المرور ");
            binding.confirmpassEtxt.requestFocus();
            return;
        }
        if (confirmpass.length() < 6) {
            binding.confirmpassEtxt.setError("ادخل كلمه مرور اكبر من 5 حروف ");
            binding.confirmpassEtxt.requestFocus();
            return;
        }
        if(!password.equals(confirmpass)){
            binding.confirmpassEtxt.setError("كلمه المرور غير متطابقه");
            binding.confirmpassEtxt.requestFocus();
            return;
        }
        if (usernametxt.isEmpty()) {
            binding.nameEtxt.setError("ادخل اسم المستخدم");
            binding.nameEtxt.requestFocus();
            return;
        }
        if (phone.isEmpty()) {
            binding.phoneEtxt.setError("ادخل رقم الجوال");
            binding.phoneEtxt.requestFocus();
            return;
        }
        if (address.isEmpty()) {
            binding.addressEtxt.setError("ادخل العنوان");
            binding.addressEtxt.requestFocus();
            return;
        }
        model.signup(usernametxt,email,phone,address,password,confirmpass);



    }
}