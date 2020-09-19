package com.example.barivarawithapi.model_class;

public class OurDataSet{
String userName;
String email;
String address;
data data;

    public com.example.barivarawithapi.model_class.data getData() {
        return data;
    }

    public void setData(com.example.barivarawithapi.model_class.data data) {
        this.data = data;
    }

    public OurDataSet(){

    }

    public OurDataSet(String userName, String email, String address){
        this.userName = userName;
        this.email = email;
        this.address = address;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
