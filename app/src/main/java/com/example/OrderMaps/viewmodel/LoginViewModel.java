package com.example.OrderMaps.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.OrderMaps.model.apimodel.SignupResponse;
import com.example.OrderMaps.utilis.InternetConnection;
import com.example.OrderMaps.utilis.Utiles;
import com.example.splashactivity.R;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class LoginViewModel extends BaseViewModel {
    public MutableLiveData<SignupResponse> loginresponse = new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public void loginuser(String phone) {
        if (InternetConnection.checkInternetConnection()) {
            setIsLoading(true);
            getRepository().LoginUser(phone).subscribe(new Observer<Response<SignupResponse>>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Response<SignupResponse> signupResponseResponse) {
                    setIsLoading(false);

                    if(signupResponseResponse.code()==200){
                        loginresponse.setValue(signupResponseResponse.body());

                    }
                    else if(signupResponseResponse.code()==401){
                        setMsgError("user name or password is incorrcet");


                    }

                }

                @Override
                public void onError(Throwable e) {
                    setIsLoading(false);
                    setMsgRes(Utiles.getMessageErrorRes(e));


                }

                @Override
                public void onComplete() {
                    setIsLoading(false);

                }
            });


        }
        else {
            setMsgRes(R.string.internet_not_connected);
        }

    }


}
