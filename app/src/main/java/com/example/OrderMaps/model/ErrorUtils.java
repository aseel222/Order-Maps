package com.example.OrderMaps.model;

import com.example.OrderMaps.api.ApiManager;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

public class ErrorUtils {
    public static BaseResponse parseError(Response<?> response) {
        Converter<ResponseBody, BaseResponse> converter =

                ApiManager.getInstance().retrofit.responseBodyConverter(BaseResponse.class,new Annotation[0]);


        BaseResponse error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new BaseResponse();
        }

        return error;
    }
}

