package com.example.OrderMaps.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.OrderMaps.model.apimodel.PendingOrderResponse;
import com.example.OrderMaps.utilis.InternetConnection;
import com.example.OrderMaps.utilis.Utiles;
import com.example.splashactivity.R;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class ProgressOrderViewModel extends BaseViewModel{
    public MutableLiveData<PendingOrderResponse> progressOrderResponseMutableLiveData=new MutableLiveData<>();



    public ProgressOrderViewModel(@NonNull Application application) {
        super(application);
    }
   public void getprogressorders(String token){
        if(InternetConnection.checkInternetConnection()){
            setIsLoading(true);
            getRepository().getallprogressorder(token).subscribe(new Observer<Response<PendingOrderResponse>>() {
                @Override
                public void onSubscribe( Disposable d) {

                }

                @Override
                public void onNext( Response<PendingOrderResponse> pendingOrderResponseResponse) {
                    if(pendingOrderResponseResponse.code()==200){
                        setIsLoading(false);

                        progressOrderResponseMutableLiveData.setValue(pendingOrderResponseResponse.body());
                    }

                }

                @Override
                public void onError( Throwable e) {
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
