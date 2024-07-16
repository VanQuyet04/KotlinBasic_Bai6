package com.example.kotlinbasic_bai6.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinbasic_bai6.model.ApiUser
import com.example.kotlinbasic_bai6.repository.UserRepository
import javax.inject.Inject

class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    val users: LiveData<List<ApiUser>> = userRepository.getUsers()
}
