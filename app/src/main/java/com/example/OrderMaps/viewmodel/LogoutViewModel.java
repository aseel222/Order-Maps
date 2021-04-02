package com.example.OrderMaps.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.OrderMaps.model.apimodel.LogOutResponse;
import com.example.OrderMaps.model.apimodel.SignupResponse;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class LogoutViewModel extends BaseViewModel {
    public MutableLiveData<LogOutResponse> logoutmutablelivedata = new MutableLiveData<>();

    public LogoutViewModel(@NonNull Application application) {
        super(application);
    }

    public void logout(String token) {
        getRepository().logout(token).subscribe(new Observer<Response<LogOutResponse>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Response<LogOutResponse> logOutResponseResponse) {
                logoutmutablelivedata.setValue(logOutResponseResponse.body());
            }


            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}


