package com.example.OrderMaps.api;


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

import java.util.Date;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @FormUrlEncoded
    @POST("auth/authapi/register")
    Observable<Response<SignupResponse>> createUser(@Field("username") String user_name, @Field("email") String email, @Field("phone") String phone, @Field("address") String address, @Field("password") String password, @Field("confirm") String confirmpassword);

    @FormUrlEncoded
    @POST("auth/authapi/login")
    Observable<Response<SignupResponse>> LoginUser(@Field("phone") String phone);

    @GET("api/orders/pending")
    Observable<Response<PendingOrderResponse>> getallpendingorder(@Header("Api-token") String token);

    @GET("api/orders/progress")
    Observable<Response<PendingOrderResponse>> getallprogressorder(@Header("Api-token") String token);

    @POST("api/providerStatus")
    Observable<Response<ProviderStatusResponse>> getstaus(@Query("key") boolean key, @Header("Api-token") String token);

    @FormUrlEncoded
    @POST("auth/authapi/verify")
    Observable<Response<SignupResponse>> verify(@Field("otp") String otp, @Header("Api-token") String token);

    @POST("auth/authapi/resend")
    Observable<Response<SignupResponse>> resend(@Header("Api-token") String token);

    @GET("api/getSingle/Orders")
    Observable<Response<OrderDetailsResponse>> getsingledetails(@Query("id") int id, @Header("Api-token") String token);

    @POST("api/orderAction")
    Observable<Response<CancelOrderResponse>> Orderstatus(@Query("id") int id, @Query("status") String status, @Header("Api-token") String token);

    @FormUrlEncoded
    @POST("auth/authapi/update")
    Observable<Response<SignupResponse>> ubdate(@Field("username") String username, @Field("email") String email, @Field("phone") String phone, @Field("address") String address, @Header("Api-token") String token);

    @GET("auth/authapi/profile")
    Observable<Response<SignupResponse>> getprofiledata(@Header("Api-token") String token);

    @GET("api/notification")
    Observable<Response<NotificationResponse>> getnotifications(@Header("Api-token") String token);

    @POST("auth/authapi/logout")
    Observable<Response<LogOutResponse>> logout(@Header("Api-token") String token);

    @FormUrlEncoded

    @POST("auth/authapi/storeFcm")
    Observable<Response<FcmTokenResponse>> storetoken(@Field("fcm") String fcmtoken, @Header("Api-token") String token);

    @POST("api/search/Orders")
    Observable<Response<PendingOrderResponse>> search(@Query("keyword") String id, @Query("status") String status, @Header("Api-token") String token);

    @GET("api/totalBillorders")
    Observable<Response<BillOrderResponse>> getbill(@Header("Api-token") String token);

    @GET("api/totalBillcompanies")
    Observable<Response<StatisticsResponse>> getstastics(@Header("Api-token") String token);

    @GET("api/totaltransaction")
    Observable<Response<HomeBillResponse>> gethomebill(@Header("Api-token") String token);

    @FormUrlEncoded
    @POST("api/supports")
    Observable<Response<SupportResponse>> supportuser(@Field("content") String content, @Header("Api-token") String token);
    @POST("api/search/Transactions")
    Observable<Response<BillOrderResponse>> postdate(@Query("from") String from,@Query("to") String to, @Header("Api-token") String token);




}
