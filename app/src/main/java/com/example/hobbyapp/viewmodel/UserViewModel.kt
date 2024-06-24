package com.example.hobbyapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hobbyapp.model.AppDB
import com.example.hobbyapp.model.User
import kotlinx.coroutines.launch
import kotlin.random.Random

class UserViewModel(private val roomDb: AppDB) : ViewModel() {
    val error = MutableLiveData<String>()
    val msg = MutableLiveData<String>()
    val user = MutableLiveData<User?>()

    var firstname: String = ""
    var lastname: String = ""
    var username: String = ""
    var password: String = ""

    fun login() {
        try {
            val result = roomDb.userDao().loginUser(username, password)
            viewModelScope.launch {
                if (result != null) {
                    result.tokenSession = "${Random.nextFloat()}+${result.username}"
                    user.value = result
                } else {
                    error.value = "User Not Found"
                }
            }
        } catch (e: Exception) {
            viewModelScope.launch {
                error.value = "User Not Found"
            }
        }
    }


    fun register() {
        val user = User(
            firstname = firstname,
            lastname = lastname,
            username = username,
            password = password
        )
        try {
            roomDb.userDao().insertUser(user)
            viewModelScope.launch {
                msg.value = "Register Success"
            }
        } catch (e: Exception) {
            viewModelScope.launch {
                error.value = e.localizedMessage ?: "Undefined Error"
            }
            return
        }
    }

    fun updateUser(newUser: User){
        try {
            roomDb.userDao().updateUser(newUser)
        } catch (e: Exception) {
            return
        }
    }

    fun getUser(id: Int) {
        try {
            val result = roomDb.userDao().getUserById(id)
            viewModelScope.launch {
                user.value = result
            }
        } catch (e: Exception) {
            viewModelScope.launch {
                msg.value = e.localizedMessage ?: ""
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}