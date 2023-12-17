package com.example.coworkingapp;

import com.example.coworkingapp.DataModal;
import com.example.coworkingapp.ui.activity.CalenderActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitAPI {
        @POST("create_account")
        Call<DataModal> userRegister(@Body DataModal dataModal);

        @POST("login")
        Call<DataModal> userAuthentication(@Body DataModal dataModal);

        @GET("get_slots?date=2023-05-01")
        Call<CalenderActivity.JSONResponse> getSlots();

}

