package com.example.OrderMaps.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.OrderMaps.model.apimodel.SupportResponse;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class SupportViewModel extends BaseViewModel {
    public MutableLiveData<SupportResponse>supportResponseMutableLiveData=new MutableLiveData<>();

    public SupportViewModel(@NonNull Application application) {
        super(application);
    }
    public void postsupport(String content, String token){

        getRepository().getsupport(content,token).subscribe(new Observer<Response<SupportResponse>>() {
            @Override
            public void onSubscribe( Disposable d) {

            }

            @Override
            public void onNext( Response<SupportResponse> supportResponseResponse) {
                if(supportResponseResponse.code()==200){
                    supportResponseMutableLiveData.setValue(supportResponseResponse.body());
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
