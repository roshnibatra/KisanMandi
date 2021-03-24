package com.example.android.vegetablemandi.ApiInterface;

import com.example.android.vegetablemandi.model.Category;
import com.example.android.vegetablemandi.model.Data;
import com.example.android.vegetablemandi.model.Grocery;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;

public interface RetrofitApi {

    @GET("/api/interview-task")
    Call<Data> getAllVegetables();

}
