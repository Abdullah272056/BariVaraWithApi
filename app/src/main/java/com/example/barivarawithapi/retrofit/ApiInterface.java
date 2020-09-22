package com.example.barivarawithapi.retrofit;


import com.example.barivarawithapi.model_class.AddresResponse;
import com.example.barivarawithapi.model_class.AddressData;
import com.example.barivarawithapi.model_class.OurDataSet;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("api/user/")
    Call<AddresResponse> getAddress();


    @POST("api/user/")
    Call<OurDataSet>postData(@Body OurDataSet ourDataSet);


    @PUT("api/user/{id}")
    Call<OurDataSet> updateUser(@Path("id") String id, @Body OurDataSet ourDataSet);

    @DELETE("api/user/{id}")
    Call<OurDataSet> deleteUser(@Path("id") String id);


}
