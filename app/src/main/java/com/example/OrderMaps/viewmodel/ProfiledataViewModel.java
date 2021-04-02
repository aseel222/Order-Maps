package com.example.OrderMaps.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.OrderMaps.model.apimodel.SignupResponse;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class ProfiledataViewModel extends  BaseViewModel{
    public MutableLiveData<SignupResponse> profiledatamutablelivedata=new MutableLiveData<>();
    public ProfiledataViewModel(@NonNull Application application) {
        super(application);
    }
    public void getprofiledata(String token){
        getRepository().getdataprofile(token).subscribe(new Observer<Response<SignupResponse>>() {
            @Override
            public void onSubscribe( Disposable d) {

            }

            @Override
            public void onNext( Response<SignupResponse> signupResponseResponse) {
                if(signupResponseResponse.code()==200){
                    profiledatamutablelivedata.setValue(signupResponseResponse.body());
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
