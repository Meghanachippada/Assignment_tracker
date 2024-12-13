package com.example.assignment_tracker

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://assignment-tracker-4ca13-default-rtdb.firebaseio.com/"

    val api: FirebaseApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FirebaseApi::class.java)
    }
}