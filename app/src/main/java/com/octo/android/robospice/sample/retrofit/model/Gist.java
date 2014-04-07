package com.octo.android.robospice.sample.retrofit.model;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

/**
 * Created by colintheshots on 4/6/14.
 */
public class Gist {
    @Expose
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @SuppressWarnings("serial")
    public static class AsList extends ArrayList<Gist> {
    }
}
