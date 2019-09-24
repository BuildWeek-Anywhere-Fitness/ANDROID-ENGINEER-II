package com.example.anywherefitness.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.anywherefitness.Api.UserApiBuilder
import com.example.anywherefitness.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel(){

    val userList = MutableLiveData<List<User>>()
    val userToken = MutableLiveData<String>()

    init {
        UserApiBuilder.userRetro().getUsers().enqueue(object : Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                println(t)
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    userList.value = response.body()
                } else
                    println("almost")
            }

        })
    }

    fun getToken(user: User) {
        UserApiBuilder.userRetro().getLoginToken(user).enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                println(t)
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    userToken.value = response.body()?.token
                    //TODO: Save token so when they open the app they don't have to log in
                } else {
                    println(response)
                }
            }

        })
    }

}


