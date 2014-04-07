package com.octo.android.robospice.sample.retrofit.network;

import com.octo.android.robospice.sample.retrofit.model.Contributor;
import com.octo.android.robospice.sample.retrofit.model.Gist;
import com.octo.android.robospice.sample.retrofit.model.GistDetail;

import retrofit.http.GET;
import retrofit.http.Path;

public interface GitHub {
    @GET("/repos/{owner}/{repo}/contributors")
    Contributor.List contributors(@Path("owner") String owner, @Path("repo") String repo);

    @GET("/gists")
    Gist.AsList gists();

    @GET("/gists/{id}")
    GistDetail gist(@Path("id") String id);
}