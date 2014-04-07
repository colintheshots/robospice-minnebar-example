package com.octo.android.robospice.sample.retrofit.network;

import com.google.gson.GsonBuilder;
import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;
import com.octo.android.robospice.sample.retrofit.RealConfig;
import com.squareup.okhttp.OkAuthenticator;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

public class TwilioSpiceService extends RetrofitGsonSpiceService {

    private final static String TWILIO_BASE_URL = "https://api.twilio.com/2010-04-01";

    @Override
    public void onCreate() {
        super.onCreate();
        addRetrofitInterface(Twilio.class);
    }

    @Override
    protected RestAdapter.Builder createRestAdapterBuilder() {
        return new RestAdapter.Builder()
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addHeader("Authorization", OkAuthenticator.Credential.basic(RealConfig.twilio_accountsid,RealConfig.twilio_authtoken).toString());
                    }
                })
                .setEndpoint(TWILIO_BASE_URL)
                .setConverter(new GsonConverter(new GsonBuilder()
                        .excludeFieldsWithoutExposeAnnotation().create()))
                .setClient(new OkClient())
                .setLogLevel(RestAdapter.LogLevel.FULL);
    }

    @Override
    protected String getServerUrl() {
        return TWILIO_BASE_URL;
    }

}
