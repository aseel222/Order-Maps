package com.example.OrderMaps.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.OrderMaps.model.apimodel.SignupResponse;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class ResendOtpViewModel extends BaseViewModel
 {
    public MutableLiveData<SignupResponse> signupResponseMutableLiveData=new MutableLiveData<>();
     public ResendOtpViewModel(@NonNull Application application) {
         super(application);
     }
     public void resendotp(String token){
         getRepository().resendcode(token).subscribe(new Observer<Response<SignupResponse>>() {
             @Override
             public void onSubscribe( Disposable d) {

             }

             @Override
             public void onNext( Response<SignupResponse> signupResponseResponse) {
                 signupResponseMutableLiveData.setValue(signupResponseResponse.body());

             }

             @Override
             public void onError( Throwable e) {

             }

             @Override
             public void onComplete() {

             }
         });
     }
 }
