package com.example.OrderMaps.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.OrderMaps.model.apimodel.StatisticsResponse;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class StatisticsViewModel  extends BaseViewModel{
    public MutableLiveData<StatisticsResponse> statisticsResponseMutableLiveData=new MutableLiveData<>();

    public StatisticsViewModel(@NonNull Application application) {
        super(application);
    }
    public void getstastics(String token){
        getRepository().getstastics(token).subscribe(new Observer<Response<StatisticsResponse>>() {
            @Override
            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

            }

            @Override
            public void onNext(@io.reactivex.annotations.NonNull Response<StatisticsResponse> statisticsResponseResponse) {
                if(statisticsResponseResponse.code()==200){
                    statisticsResponseMutableLiveData.setValue(statisticsResponseResponse.body());
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
