package com.octo.android.robospice.sample.retrofit.network.requests;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;
import com.octo.android.robospice.sample.retrofit.model.GistDetail;
import com.octo.android.robospice.sample.retrofit.network.GitHub;

/**
 * Created by colintheshots on 4/6/14.
 */
public class GistRequest extends RetrofitSpiceRequest<GistDetail, GitHub> {

    private String id;

    public GistRequest(String id) {
        super(GistDetail.class, GitHub.class);
        this.id = id;
    }

    @Override
    public GistDetail loadDataFromNetwork() throws Exception {
        return getService().gist(id);
    }
}
