package com.octo.android.robospice.sample.retrofit.network;

import com.octo.android.robospice.sample.retrofit.model.CallResponse;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import retrofit.http.Path;

public interface Twilio {

    @FormUrlEncoded
    @POST("/Accounts/{accountsid}/Calls.json")
    CallResponse makeCall(
            @Path("accountsid") String accountsid,
            @Field("From") String from,
            @Field("To") String to,
            @Field("Url") String url
    );

}