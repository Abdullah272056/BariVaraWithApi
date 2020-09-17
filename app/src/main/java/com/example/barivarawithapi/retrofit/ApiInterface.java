package com.example.barivarawithapi.retrofit;


import com.example.barivarawithapi.model_class.AddresResponse;
import com.example.barivarawithapi.model_class.UpdateResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {


    @GET("api/user/?fbclid=IwAR33Kd6mJiUBeNjwW4KXeQyk7AtU3kMC7r4ISYJpvtMD4bnMmUjyMxhi2_Y")
    Call<AddresResponse> getAddress();

    @FormUrlEncoded
    @POST("api/user/")
    Call<UpdateResponse> addData(@Field("userName") String userName,@Field("email") String email, @Field("address") String address);



}
