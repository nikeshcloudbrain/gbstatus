package com.gblatestversion.gbversion.gb.api.ads;

import com.gblatestversion.gbversion.gb.utils.Constant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GRenClient {

    public static String BASE_URL = Constant.getMainApi();

    private static GRenClient retrofitCLient;

    private Retrofit retroMain;
    private Retrofit retroApps;

    public GRenClient() {


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(
                        chain -> {
                            Request original = chain.request();

                            Request.Builder requestBuilder = original.newBuilder()
                                    .method(original.method(), original.body());

                            Request request = requestBuilder.build();
                            return chain.proceed(request);
                        }
                ).build();


        retroMain = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();


    }

    public static synchronized GRenClient getInstance() {
        if (retrofitCLient == null || BASE_URL == null ) {
            retrofitCLient = new GRenClient();
        }
        return retrofitCLient;
    }

    public GApiInter getGApi() {
        return retroMain.create(GApiInter.class);
    }

}
