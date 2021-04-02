package com.example.OrderMaps.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.OrderMaps.model.apimodel.orderdetailsmodel.OrderDetailsResponse;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class OrderDetailsViewModel extends  BaseViewModel {
    public MutableLiveData<OrderDetailsResponse>orderDetailsResponseMutableLiveData=new MutableLiveData<>();

    public OrderDetailsViewModel(@NonNull Application application) {

        super(application);
    }
    public void getorderdetails(int id,String token){
        getRepository().getdetails(id, token).subscribe(new Observer<Response<OrderDetailsResponse>>() {
            @Override
            public void onSubscribe( Disposable d) {

            }

            @Override
            public void onNext( Response<OrderDetailsResponse> orderDetailsResponseResponse) {
                if(orderDetailsResponseResponse.code()==200){
                    orderDetailsResponseMutableLiveData.setValue(orderDetailsResponseResponse.body());
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
