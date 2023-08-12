package com.gblatestversion.gbversion.gb.api.ads;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface GApiInter {

    @POST("update.php")
    @FormUrlEncoded
    Call<ResponseBody> getUpdatesResponse(@Field("data") String requestBody);
}