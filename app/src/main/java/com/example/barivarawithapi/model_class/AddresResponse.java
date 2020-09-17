package com.example.barivarawithapi.model_class;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddresResponse {
    @SerializedName("data")
    @Expose
    private List<AddressData> data = null;

    public AddresResponse(List<AddressData> data) {
        this.data = data;
    }

    public List<AddressData> getData() {
        return data;
    }

    public void setData(List<AddressData> data) {
        this.data = data;
    }


}

