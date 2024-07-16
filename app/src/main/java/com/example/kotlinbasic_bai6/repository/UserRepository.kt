package com.example.kotlinbasic_bai6.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlinbasic_bai6.model.ApiUser
import com.example.kotlinbasic_bai6.network.ApiResponse
import com.example.kotlinbasic_bai6.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(private val apiService: ApiService) {

    fun getUsers(): LiveData<List<ApiUser>> {
        val data = MutableLiveData<List<ApiUser>>()

        apiService.getUsers().enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    data.value = response.body()?.results
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                data.value = emptyList() // Handle error case appropriately
            }
        })

        return data
    }
}
