package com.example.barivarawithapi.retrofit;


import com.example.barivarawithapi.model_class.AddresResponse;
import com.example.barivarawithapi.model_class.OurDataSet;
import com.example.barivarawithapi.model_class.UpdateResponse;
import com.google.gson.JsonObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {


    @GET("api/user/?fbclid=IwAR33Kd6mJiUBeNjwW4KXeQyk7AtU3kMC7r4ISYJpvtMD4bnMmUjyMxhi2_Y")
    Call<AddresResponse> getAddress();


    @POST("api/user/")
    Call<OurDataSet>postData(@Body OurDataSet ourDataSet);



}
