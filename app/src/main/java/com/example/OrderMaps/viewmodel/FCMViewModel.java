package com.example.OrderMaps.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.OrderMaps.model.apimodel.FcmTokenResponse;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class FCMViewModel extends BaseViewModel {
    public MutableLiveData<FcmTokenResponse> fcmTokenResponseMutableLiveData=new MutableLiveData<>();

    public FCMViewModel(@NonNull Application application) {
        super(application);
    }
    public void storetoken(String fcm,String token){
        getRepository().storetoken(fcm,token).subscribe(new Observer<Response<FcmTokenResponse>>() {
            @Override
            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

            }

            @Override
            public void onNext(@io.reactivex.annotations.NonNull Response<FcmTokenResponse> fcmTokenResponseResponse) {
if(fcmTokenResponseResponse.code()==200){
    fcmTokenResponseMutableLiveData.setValue(fcmTokenResponseResponse.body());
}
            }

            @Override
            public void onError(@io.reactivex.annotations.NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}

