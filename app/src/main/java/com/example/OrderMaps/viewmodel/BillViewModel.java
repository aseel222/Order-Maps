package com.example.OrderMaps.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.OrderMaps.model.apimodel.BillOrderResponse;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class BillViewModel extends BaseViewModel{
    public MutableLiveData<BillOrderResponse>billOrderResponseMutableLiveData=new MutableLiveData<>();
    public BillViewModel(@NonNull Application application) {
        super(application);
    }
    public void getbilldata(String token){
        getRepository().getbill(token).subscribe(new Observer<Response<BillOrderResponse>>() {
            @Override
            public void onSubscribe( Disposable d) {

            }

            @Override
            public void onNext( Response<BillOrderResponse> billOrderResponseResponse) {
                if(billOrderResponseResponse.code()==200){
                    billOrderResponseMutableLiveData.setValue(billOrderResponseResponse.body());

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
