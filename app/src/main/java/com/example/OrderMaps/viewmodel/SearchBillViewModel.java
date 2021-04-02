package com.example.OrderMaps.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.OrderMaps.model.apimodel.BillOrderResponse;
import com.example.OrderMaps.model.apimodel.SearchbillResponse;
import com.example.OrderMaps.utilis.InternetConnection;

import java.util.Date;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class SearchBillViewModel extends BaseViewModel {
    public MutableLiveData<BillOrderResponse> billOrderResponseMutableLiveData = new MutableLiveData<>();

    public SearchBillViewModel(@NonNull Application application) {
        super(application);
    }

    public void postdate(String from, String to, String token) {
        if (InternetConnection.checkInternetConnection()) {
            setIsLoading(true);
            getRepository().postdate(from, to, token).subscribe(new Observer<Response<BillOrderResponse>>() {
                @Override
                public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                }

                @Override
                public void onNext(@io.reactivex.annotations.NonNull Response<BillOrderResponse> billOrderResponseResponse) {
                    if(billOrderResponseResponse.code()==200){
                        setIsLoading(false);
                        billOrderResponseMutableLiveData.setValue(billOrderResponseResponse.body());
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
}
