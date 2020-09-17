package com.example.barivarawithapi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.example.barivarawithapi.model_class.AddresResponse;
import com.example.barivarawithapi.model_class.AddressData;
import com.example.barivarawithapi.model_class.UpdateResponse;
import com.example.barivarawithapi.retrofit.ApiInterface;
import com.example.barivarawithapi.retrofit.RetrofitClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ApiInterface apiInterface,apiInterface1;
    List<AddressData> allAddress;
    RecyclerView recyclerView;
    CustomAdapter adapter;
    FloatingActionButton addButton;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButton=findViewById(R.id.addButtonId);

        //initialize apiInterface
        apiInterface = RetrofitClient.getRetrofit("https://demo-friendstech.herokuapp.com/").create(ApiInterface.class);
      //  apiInterface1 = RetrofitClient.getRetrofit("https://demo-friendstech.herokuapp.com/").create(ApiInterface.class);

        recyclerView = findViewById(R.id.recyclerView);

        //api call to get the data
        getAllFlowerData();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiInterface.addData("ab","zz@gmail.com","dhaka").enqueue(new Callback<UpdateResponse>(){
                    @Override
                    public void onResponse(Call<UpdateResponse> call, Response<UpdateResponse> response) {
                        Log.wtf("Abdullah",new Gson().toJson(response.body()));
                        if (response.isSuccessful()){
                        if (response.body().getSuccess()){
                            Toast.makeText(MainActivity.this, "add success", Toast.LENGTH_SHORT).show();
                        }
                    }
                    }
                    @Override
                    public void onFailure(Call<UpdateResponse> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "add fail", Toast.LENGTH_SHORT).show();
                    }
                });
                Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void getAllFlowerData(){
        apiInterface.getAddress().enqueue(new Callback<AddresResponse>(){
            @Override
            public void onResponse(Call<AddresResponse> call, Response<AddresResponse> response) {
                if (response.isSuccessful()){
                    allAddress  = new ArrayList<>();
                    allAddress.addAll(response.body().getData());
                    if (allAddress.size ()>0){
                        adapter = new CustomAdapter(MainActivity.this,allAddress);
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        recyclerView.setAdapter(adapter);
                        Toast.makeText (MainActivity.this, String.valueOf (allAddress.size ()), Toast.LENGTH_SHORT).show ();
                    }

                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddresResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_SHORT).show();

            }
        });
    }
}