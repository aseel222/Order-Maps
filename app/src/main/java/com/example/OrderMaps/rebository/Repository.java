package com.example.OrderMaps.rebository;


import com.example.OrderMaps.api.ApiManager;
import com.example.OrderMaps.api.ApiService;
import com.example.OrderMaps.model.BaseResponse;
import com.example.OrderMaps.model.apimodel.BillOrderResponse;
import com.example.OrderMaps.model.apimodel.CancelOrderResponse;
import com.example.OrderMaps.model.apimodel.FcmTokenResponse;
import com.example.OrderMaps.model.apimodel.HomeBillResponse;
import com.example.OrderMaps.model.apimodel.LogOutResponse;
import com.example.OrderMaps.model.apimodel.NotificationResponse;
import com.example.OrderMaps.model.apimodel.PendingOrderResponse;
import com.example.OrderMaps.model.apimodel.ProviderStatusResponse;
import com.example.OrderMaps.model.apimodel.SearchbillResponse;
import com.example.OrderMaps.model.apimodel.SignupResponse;
import com.example.OrderMaps.model.apimodel.StatisticsResponse;
import com.example.OrderMaps.model.apimodel.SupportResponse;
import com.example.OrderMaps.model.apimodel.orderdetailsmodel.OrderDetailsResponse;
import com.example.OrderMaps.model.apimodel.orderdetailsmodel.SearchResponse;

import java.util.Date;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public final class Repository {

    private static Repository instance;
    private static ApiService service;
    //private Context context;

    private Repository(/*Context context*/) {
       // this.context = context;
        service = ApiManager.getInstance().getApi();
    }

    public static Repository getInstance(/*Context context*/) {
        if (instance == null)
            instance = new Repository(/*context*/);
        return instance;
    }
    public Observable<Response<SignupResponse>> createUser(String userName, String email, String phone , String addrss, String password, String confirmpassword) {
        return service.createUser(userName,email,phone , addrss,password,confirmpassword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<Response<SignupResponse>> LoginUser(String phone) {
        return service.LoginUser(phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<Response<PendingOrderResponse>> getallpendingorder(String token) {
        return service.getallpendingorder(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<Response<PendingOrderResponse>> getallprogressorder(String token) {
        return service.getallprogressorder(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<Response<ProviderStatusResponse>> getstatus(boolean key,String token) {
        return service.getstaus(key,token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<Response<SignupResponse>> getverify(String otp,String token) {
        return service.verify(otp,token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<Response<SignupResponse>> resendcode(String token) {
        return service.resend(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<Response<OrderDetailsResponse>> getdetails(int id,String token) {
        return service.getsingledetails(id,token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<Response<CancelOrderResponse>> getorderstatus(int id, String status,String token) {
        return service.Orderstatus(id,status,token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<Response<SignupResponse>> ubdate(String username,String email,String phone,String address,String token) {
        return service.ubdate(username,email,phone,address,token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<Response<SignupResponse>> getdataprofile(String token) {
        return service.getprofiledata(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<Response<NotificationResponse>> getnotifications(String token) {
        return service.getnotifications(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<Response<LogOutResponse>> logout(String token) {
        return service.logout(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<Response<FcmTokenResponse>> storetoken(String fcmtoken,String token) {
        return service.storetoken(fcmtoken,token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<Response<PendingOrderResponse>> search(String id, String status,String token) {
        return service.search(id,status,token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<Response<BillOrderResponse>> getbill(String token) {
        return service.getbill(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<Response<StatisticsResponse>> getstastics(String token) {
        return service.getstastics(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Response<HomeBillResponse>> getHomebill(String token) {
        return service.gethomebill(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<Response<SupportResponse>> getsupport(String content,String token) {
        return service.supportuser(content,token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<Response<BillOrderResponse>> postdate(String from, String to,String token) {
        return service.postdate(from,to,token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }






}
