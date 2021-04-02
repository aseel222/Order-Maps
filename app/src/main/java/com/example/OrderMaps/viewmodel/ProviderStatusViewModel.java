package com.example.OrderMaps.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.OrderMaps.model.apimodel.ProviderStatusResponse;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class ProviderStatusViewModel extends BaseViewModel {
    public MutableLiveData<ProviderStatusResponse> providerStatusResponseMutableLiveData=new MutableLiveData<>();

    public ProviderStatusViewModel(@NonNull Application application) {
        super(application);
    }
    public void getstatus(boolean key,String token){
        getRepository().getstatus(key,token).subscribe(new Observer<Response<ProviderStatusResponse>>() {
            @Override
            public void onSubscribe( Disposable d) {

            }

            @Override
            public void onNext( Response<ProviderStatusResponse> providerStatusResponseResponse) {
                if(providerStatusResponseResponse.code()==200){
                    providerStatusResponseMutableLiveData.setValue(providerStatusResponseResponse.body());
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
