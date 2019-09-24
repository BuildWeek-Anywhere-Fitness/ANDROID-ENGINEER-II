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

    init {
        UserApiBuilder.userRetro().getUser().enqueue(object : Callback<List<User>> {
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


}
