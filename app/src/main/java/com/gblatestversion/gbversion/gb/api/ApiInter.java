package com.gblatestversion.gbversion.gb.api;


import com.gblatestversion.gbversion.gb.model.DpGen.Example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiInter {

    @GET("instore/ring-category")
    @Headers({"AuthorizationKey: abcdefgh"})
    Call<Example> getDpGen();
}