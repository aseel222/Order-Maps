package com.example.OrderMaps.ui.activities;

 import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
 import android.util.Patterns;
 import android.view.View;
 import android.widget.Toast;

 import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
 import androidx.lifecycle.Observer;
 import androidx.lifecycle.ViewModelProvider;

 import com.example.OrderMaps.model.apimodel.SignupResponse;
 import com.example.OrderMaps.model.local.PreferencesManager;
 import com.example.OrderMaps.viewmodel.LoginViewModel;
 import com.example.OrderMaps.viewmodel.SignupViewModel;
 import com.example.splashactivity.R;
import com.example.splashactivity.databinding.ActivityLoginBinding;


public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    String sourceString ="ليس لديك حساب؟ "+"<b>" + "انشاء حساب" + "</b> ";
    String phone;
    LoginViewModel model;
    PreferencesManager preferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_login);
        model = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.sinuptxt.setText(Html.fromHtml(sourceString));
        preferencesManager=new PreferencesManager(this);
        observables();
        binding.sinuptxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
        binding.loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               validationlogin();
            }
        });
//        binding.forgetpasswordTxt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(LoginActivity.this, ForgetPasswordActivity.class);
//                startActivity(intent);
//            }
//        });
    }
    public void observables(){
        model.loginresponse.observe(this, new Observer<SignupResponse>() {
            @Override
            public void onChanged(SignupResponse signupResponse) {
                preferencesManager.savetoken(signupResponse.getData().getAccessToken());


                Intent intent=new Intent(LoginActivity.this, OtpActivity.class);
                startActivity(intent);
            }
        });
        model.getMsgError().observe(this, s -> {
            Toast.makeText(this,"اكتب رقم هاتف صحيح",Toast.LENGTH_LONG).show();
        });
        model.isLoading().observe(this, isloading -> {
            if (isloading) {
                binding.loading.setVisibility(View.VISIBLE);
            } else {
                binding.loading.setVisibility(View.GONE);
            }
        });
        model.getMsgRes().observe(this, res -> {
            Toast.makeText(this,"لا يوجد إتصال بالإنترنت",Toast.LENGTH_LONG).show();
        });





    }
    public void validationlogin(){
        phone=binding.phoneEtxt.getText().toString().trim();

        if (phone.isEmpty()) {
            binding.phoneEtxt.setError("ادخل رقم الجوال");
            binding.phoneEtxt.requestFocus();


            return;
        }


        model.loginuser(phone);



    }
}