package com.example.OrderMaps.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.OrderMaps.model.apimodel.HomeBillResponse;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class HomeBillViewModel extends BaseViewModel {
    public MutableLiveData<HomeBillResponse> homeBillResponseMutableLiveData=new MutableLiveData<>();

    public HomeBillViewModel(@NonNull Application application) {
        super(application);
    }
    public void gethomebill(String token){
        getRepository().getHomebill(token).subscribe(new Observer<Response<HomeBillResponse>>() {
            @Override
            public void onSubscribe( Disposable d) {

            }

            @Override
            public void onNext( Response<HomeBillResponse> homeBillResponseResponse) {
                if(homeBillResponseResponse.code()==200){
                    homeBillResponseMutableLiveData.setValue(homeBillResponseResponse.body());
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
