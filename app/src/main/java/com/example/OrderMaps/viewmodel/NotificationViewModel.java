package com.example.OrderMaps.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.OrderMaps.model.apimodel.NotificationResponse;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class NotificationViewModel extends BaseViewModel{
    public MutableLiveData<NotificationResponse> notificationResponseMutableLiveData=new MutableLiveData<>();

    public NotificationViewModel(@NonNull Application application) {
        super(application);
    }
    public void getnotifications(String token){
        getRepository().getnotifications(token).subscribe(new Observer<Response<NotificationResponse>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext( Response<NotificationResponse> notificationResponseResponse) {
                if(notificationResponseResponse.code()==200){
                    notificationResponseMutableLiveData.setValue(notificationResponseResponse.body());
                }

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
