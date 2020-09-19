package com.example.barivarawithapi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.barivarawithapi.model_class.AddresResponse;
import com.example.barivarawithapi.model_class.AddressData;
import com.example.barivarawithapi.model_class.OurDataSet;
import com.example.barivarawithapi.retrofit.ApiInterface;
import com.example.barivarawithapi.retrofit.RetrofitClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText nameEditText,emailEditText,addressEditText;
    Button cancelButton,saveButton;
    String name,email,address;

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
                    }
                }
            }
            @Override
            public void onFailure(Call<AddresResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addData(View v) {
        AlertDialog.Builder builder     =new AlertDialog.Builder(MainActivity.this);
        LayoutInflater layoutInflater   =LayoutInflater.from(MainActivity.this);
        View view                       =layoutInflater.inflate(R.layout.input_box,null);
        builder.setView(view);
        final AlertDialog alertDialog   = builder.create();

         nameEditText       =view.findViewById(R.id.nameEditTextId);
         emailEditText      =view.findViewById(R.id.emailEditTextId);
         addressEditText    =view.findViewById(R.id.addressEditTextId);
        cancelButton        =view.findViewById(R.id.cancelButtonId);
        saveButton          =view.findViewById(R.id.saveButtonId);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name        =nameEditText.getText().toString();
                email       =emailEditText.getText().toString();
                address     =addressEditText.getText().toString();

                Retrofit retrofit=new Retrofit.Builder().baseUrl("https://demo-friendstech.herokuapp.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                ApiInterface apiInterface   =retrofit.create(ApiInterface.class);
                OurDataSet ourDataSet       =new OurDataSet(name,email,address);
                 Call<OurDataSet>call       =apiInterface.postData(ourDataSet);

                 call.enqueue(new Callback<OurDataSet>(){
                     @Override
                     public void onResponse(Call<OurDataSet> call, Response<OurDataSet> response) {
                         getAllFlowerData();
                         Toast.makeText(MainActivity.this, response.body().getData().getMsg(), Toast.LENGTH_SHORT).show();
                     }
                     @Override
                     public void onFailure(Call<OurDataSet> call, Throwable t){
                         Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_SHORT).show();
                     }
                 });

                alertDialog.dismiss();
            }
        });


        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }
}