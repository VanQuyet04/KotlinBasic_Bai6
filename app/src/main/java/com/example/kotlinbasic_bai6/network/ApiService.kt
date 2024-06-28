package com.example.kotlinbasic_bai6.network

import com.example.kotlinbasic_bai6.model.ApiUser
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("?results=20")
    fun getUsers(): Call<ApiResponse>
}

data class ApiResponse(val results: List<ApiUser>)
