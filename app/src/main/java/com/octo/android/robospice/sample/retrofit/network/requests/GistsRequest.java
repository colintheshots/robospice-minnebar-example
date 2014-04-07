package com.octo.android.robospice.sample.retrofit.network.requests;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;
import com.octo.android.robospice.sample.retrofit.model.Gist;
import com.octo.android.robospice.sample.retrofit.network.GitHub;

import roboguice.util.temp.Ln;

public class GistsRequest extends RetrofitSpiceRequest<Gist.AsList, GitHub> {

    public GistsRequest() {
        super(Gist.AsList.class, GitHub.class);
    }

    @Override
    public Gist.AsList loadDataFromNetwork() {
        Ln.d("Call web service ");
        return getService().gists();
    }
}
