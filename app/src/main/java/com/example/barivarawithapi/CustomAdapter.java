package com.example.barivarawithapi;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barivarawithapi.model_class.AddressData;
import com.example.barivarawithapi.model_class.OurDataSet;
import com.example.barivarawithapi.retrofit.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    Context context;
    List<AddressData> dataList;
    TextView editTextView,deleteTextView,cancelTextView;


    EditText nameEditText,emailEditText,addressEditText;
    Button cancelButton,saveButton;
    String name,email,address;
    String id;


    public CustomAdapter(Context context, List<AddressData> dataList) {
        this.context    = context;
        this.dataList   = dataList;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_design,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.nameTextView.setText(dataList.get(position).getUserName());
        holder.emailTextView.setText(dataList.get(position).getEmail());
        holder.addressTextView.setText(dataList.get(position).getAddress());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder     =new AlertDialog.Builder(context);
                LayoutInflater layoutInflater   =LayoutInflater.from(context);
                View view       =layoutInflater.inflate(R.layout.row_operation,null);
                builder.setView(view);
                final AlertDialog alertDialog=builder.create();
               editTextView     = view.findViewById(R.id.editTextViewId);
               deleteTextView   = view.findViewById(R.id.deleteTextViewId);
               cancelTextView   = view.findViewById(R.id.cancelTextViewId);
                id=dataList.get(position).getId();
                editTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();

                        androidx.appcompat.app.AlertDialog.Builder builder     =new androidx.appcompat.app.AlertDialog.Builder(context);
                        LayoutInflater layoutInflater   =LayoutInflater.from(context);
                        View view                       =layoutInflater.inflate(R.layout.input_box,null);
                        builder.setView(view);
                        final androidx.appcompat.app.AlertDialog alertDialog   = builder.create();

                        nameEditText       =view.findViewById(R.id.nameEditTextId);
                        emailEditText      =view.findViewById(R.id.emailEditTextId);
                        addressEditText    =view.findViewById(R.id.addressEditTextId);
                        cancelButton        =view.findViewById(R.id.cancelButtonId);
                        saveButton          =view.findViewById(R.id.saveButtonId);

                        nameEditText.setText(dataList.get(position).getUserName());
                        emailEditText.setText(dataList.get(position).getEmail());
                        addressEditText.setText(dataList.get(position).getAddress());



                        saveButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                name        =nameEditText.getText().toString();
                                email       =emailEditText.getText().toString();
                                address     =addressEditText.getText().toString();

                               // AddressData addressData=new AddressData(id,name,email,address);
                                OurDataSet ourDataSet       =new OurDataSet(name,email,address);
                                Retrofit retrofit=new Retrofit.Builder().baseUrl("https://demo-friendstech.herokuapp.com/")
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();
                                ApiInterface apiInterface   =retrofit.create(ApiInterface.class);

                                Call<OurDataSet>call       =apiInterface.updateUser(id,ourDataSet);
//
                                call.enqueue(new Callback<OurDataSet>(){
                                    @Override
                                    public void onResponse(Call<OurDataSet> call, Response<OurDataSet> response) {
                                        Toast.makeText(context, response.body().getData().getUserName(), Toast.LENGTH_SHORT).show();

                                        //getAllFlowerData();
                                       // Toast.makeText(context, response.body().getData().getMsg(), Toast.LENGTH_SHORT).show();
                                    }
                                    @Override
                                    public void onFailure(Call<OurDataSet> call, Throwable t){
                                       // Toast.makeText(context, "fail", Toast.LENGTH_SHORT).show();
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
                       // Toast.makeText(context, "edit", Toast.LENGTH_SHORT).show();

                    }
                });
                deleteTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://demo-friendstech.herokuapp.com/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        ApiInterface apiInterface   =retrofit.create(ApiInterface.class);

                        Call<OurDataSet>call       =apiInterface.deleteUser(id);
                        call.enqueue(new Callback<OurDataSet>() {
                            @Override
                            public void onResponse(Call<OurDataSet> call, Response<OurDataSet> response) {
                                Toast.makeText(context, "delete Success", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<OurDataSet> call, Throwable t) {

                            }
                        });


                        //Toast.makeText(context, "delete", Toast.LENGTH_SHORT).show();

                        alertDialog.dismiss();
                    }
                });

               cancelTextView.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Toast.makeText(context, "cancel", Toast.LENGTH_SHORT).show();

                       alertDialog.dismiss();
                   }
               });

                alertDialog.show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView,emailTextView,addressTextView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView        = itemView.findViewById(R.id.nameTextViewId);
            emailTextView       = itemView.findViewById(R.id.emailTextViewId);
            addressTextView     = itemView.findViewById(R.id.addressTextViewId);
        }
    }
}
