package com.example.OrderMaps.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.OrderMaps.model.BaseResponse;
import com.example.OrderMaps.model.ErrorUtils;
import com.example.OrderMaps.model.apimodel.SignupResponse;
import com.example.OrderMaps.model.apimodel.orderdetailsmodel.EmailFailed;
import com.example.OrderMaps.utilis.InternetConnection;
import com.example.OrderMaps.utilis.Utiles;
import com.example.splashactivity.R;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class SignupViewModel extends BaseViewModel {

    public MutableLiveData<SignupResponse> signupResponseMutableLiveData = new MutableLiveData<>();


    public SignupViewModel(@NonNull Application application) {
        super(application);
    }

    public void signup(String name, String email, String phone, String address, String password, String confirmpass) {
        if (InternetConnection.checkInternetConnection()) {
            setIsLoading(true);
            getRepository().createUser(name, email, phone, address, password, confirmpass)
                    .subscribe(new Observer<Response<SignupResponse>>() {
                @Override
                public void onSubscribe(Disposable d) {


                }

                @Override
                public void onNext(Response<SignupResponse> signupResponseResponse) {
                    if (signupResponseResponse.code() == 200) {
                        setIsLoading(false);
                        signupResponseMutableLiveData.setValue(signupResponseResponse.body());

                    } else if (signupResponseResponse.code() == 401) {
                        BaseResponse baseResponse= ErrorUtils.parseError(signupResponseResponse);
                        setMsgError(baseResponse.getData());
                        setIsLoading(true);

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
        } else {
            setMsgRes(R.string.internet_not_connected);
        }


    }


}
