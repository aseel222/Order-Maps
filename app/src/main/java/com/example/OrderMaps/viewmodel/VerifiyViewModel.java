package com.example.OrderMaps.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.OrderMaps.model.apimodel.SignupResponse;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class VerifiyViewModel  extends BaseViewModel{
    public MutableLiveData<SignupResponse> signupResponseMutableLiveData=new MutableLiveData<>();


    public VerifiyViewModel(@NonNull Application application) {
        super(application);
    }
    public void getverifycode(String otp,String token){
        getRepository().getverify(otp,token).subscribe(new Observer<Response<SignupResponse>>() {
            @Override
            public void onSubscribe( Disposable d) {

            }

            @Override
            public void onNext( Response<SignupResponse> signupResponseResponse) {
                if(signupResponseResponse.code()==200){
                    signupResponseMutableLiveData.setValue(signupResponseResponse.body());
                }
                else if(signupResponseResponse.code()==401){
                    setMsgError("");
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
