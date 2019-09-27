package com.example.anywherefitness.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.anywherefitness.Api.UserApiBuilder
import com.example.anywherefitness.model.CreateUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel: ViewModel() {

    val isSuccessful = MutableLiveData<Boolean>()

    fun createUser(user: CreateUser) {
        UserApiBuilder.userRetro().addUser(user).enqueue(object : Callback<CreateUser> {
            override fun onFailure(call: Call<CreateUser>, t: Throwable) {
                println(t)
            }
            override fun onResponse(call: Call<CreateUser>, response: Response<CreateUser>) {
                if (response.isSuccessful) {
                    isSuccessful.value = true
                } else {
                    println(response)
                }
            }
        })
    }
}