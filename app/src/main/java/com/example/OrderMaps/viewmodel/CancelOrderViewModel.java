package com.example.OrderMaps.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.OrderMaps.model.apimodel.CancelOrderResponse;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class CancelOrderViewModel extends  BaseViewModel{
    public MutableLiveData<CancelOrderResponse> cancelOrderResponseMutableLiveData=new MutableLiveData<>();
    public CancelOrderViewModel(@NonNull Application application) {
        super(application);
    }
    public void postorderstatus(int id ,String status,String token){
        getRepository().getorderstatus(id,status,token).subscribe(new Observer<Response<CancelOrderResponse>>() {
            @Override
            public void onSubscribe( Disposable d) {

            }

            @Override
            public void onNext( Response<CancelOrderResponse> cancelOrderResponseResponse) {
                if(cancelOrderResponseResponse.code()==200){
                    cancelOrderResponseMutableLiveData.setValue(cancelOrderResponseResponse.body());
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
