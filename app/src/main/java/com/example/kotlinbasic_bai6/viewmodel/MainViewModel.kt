package com.example.kotlinbasic_bai6.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinbasic_bai6.model.ApiUser
import com.example.kotlinbasic_bai6.repository.UserRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val userRepository = UserRepository()
    private val _users = MutableLiveData<List<ApiUser>>()
    val users: LiveData<List<ApiUser>> get() = _users

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            userRepository.getUsers().observeForever { users ->
                _users.value = users
            }
        }
    }
}
