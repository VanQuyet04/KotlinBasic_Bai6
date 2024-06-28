package com.example.kotlinbasic_bai6.repository

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlinbasic_bai6.model.ApiUser
import com.example.kotlinbasic_bai6.network.ApiResponse
import com.example.kotlinbasic_bai6.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {
    private val apiService = RetrofitClient.instance

    fun getUsers(): LiveData<List<ApiUser>> {
        val data = MutableLiveData<List<ApiUser>>()

        apiService.getUsers().enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    data.value = response.body()?.results
                }
            }

            @SuppressLint("NullSafeMutableLiveData")
            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                data.value = null
            }
        })

        return data
    }
}
