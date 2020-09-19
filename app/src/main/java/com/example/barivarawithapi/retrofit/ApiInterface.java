package com.example.barivarawithapi.retrofit;


import com.example.barivarawithapi.model_class.AddresResponse;
import com.example.barivarawithapi.model_class.AddressData;
import com.example.barivarawithapi.model_class.OurDataSet;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {

    //@GET("api/user/?fbclid=IwAR33Kd6mJiUBeNjwW4KXeQyk7AtU3kMC7r4ISYJpvtMD4bnMmUjyMxhi2_Y")
    @GET("api/user/")
    Call<AddresResponse> getAddress();


    @POST("api/user/")
    Call<OurDataSet>postData(@Body OurDataSet ourDataSet);

    @PUT("api/user/{id}")
    Call<OurDataSet> updateUser(@Path("id") String id, @Body OurDataSet ourDataSet);




}
