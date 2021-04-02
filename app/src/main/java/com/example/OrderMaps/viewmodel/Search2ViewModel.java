package com.example.OrderMaps.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.OrderMaps.model.apimodel.PendingOrderResponse;
import com.example.OrderMaps.utilis.InternetConnection;
import com.example.OrderMaps.utilis.Utiles;
import com.example.splashactivity.R;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class Search2ViewModel extends BaseViewModel {
    public MutableLiveData<PendingOrderResponse> searchResponseMutableLiveData = new MutableLiveData<>();

    public Search2ViewModel(@NonNull Application application) {
        super(application);
    }

    public void search(String id,String status, String token) {
        if(InternetConnection.checkInternetConnection()){
            setIsLoading(true);
            getRepository().search(id,status, token).subscribe(new Observer<Response<PendingOrderResponse>>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Response<PendingOrderResponse> pendingOrderResponseResponse) {
                    if(pendingOrderResponseResponse.code()==200){
                        setIsLoading(false);
                        searchResponseMutableLiveData.setValue(pendingOrderResponseResponse.body());
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
        else{

            setMsgRes(R.string.internet_not_connected);
        }

    }
}
