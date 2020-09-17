package com.example.barivarawithapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barivarawithapi.model_class.AddresResponse;
import com.example.barivarawithapi.model_class.AddressData;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    Context context;
    List<AddressData> dataList;

    public CustomAdapter(Context context, List<AddressData> dataList) {
        this.context = context;
        this.dataList = dataList;
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
        holder.itemView.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Toast.makeText (context, String.valueOf (dataList.get (position).getUserName ()), Toast.LENGTH_SHORT).show ();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView,emailTextView,addressTextView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView  = itemView.findViewById(R.id.nameTextViewId);
            emailTextView      = itemView.findViewById(R.id.emailTextViewId);
            addressTextView      = itemView.findViewById(R.id.addressTextViewId);
        }
    }
}
