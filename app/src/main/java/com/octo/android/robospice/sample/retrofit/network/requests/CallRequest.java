package com.octo.android.robospice.sample.retrofit.network.requests;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;
import com.octo.android.robospice.sample.retrofit.model.CallResponse;
import com.octo.android.robospice.sample.retrofit.network.Twilio;

/**
 * Created by colintheshots on 4/5/14.
 */
public class CallRequest extends RetrofitSpiceRequest<CallResponse, Twilio> {

    private String accountsid;
    private String from;
    private String to;
    private String url;

    public CallRequest(String accountsid, String from, String to, String url) {
        super(CallResponse.class, Twilio.class);
        this.accountsid = accountsid;
        this.from = from;
        this.to = to;
        this.url = url;
    }

    @Override
    public CallResponse loadDataFromNetwork() throws Exception {
        return getService().makeCall(accountsid, from, to, url);
    }
}
