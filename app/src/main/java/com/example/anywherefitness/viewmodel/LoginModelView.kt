package com.example.anywherefitness.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.anywherefitness.Api.UserApiBuilder
import com.example.anywherefitness.model.User
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel(){

    val userToken = MutableLiveData<String>()
    val currentUser = MutableLiveData<User>()



    fun getToken(user: User) {

        UserApiBuilder.userRetro().getLoginToken(user).enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                userToken.value = null
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    userToken.value = response.body()?.token
                } else {
                    userToken.value = null
                }
            }

        })
    }

    fun getCurrentUser(token: String){
        val splitToken = token.split(".")
        val codedData = splitToken[1]
        val decodedByte = android.util.Base64.decode(codedData, android.util.Base64.DEFAULT)
        val decodedString = String(decodedByte)
        val stringToJSON = JSONObject(decodedString)
        val getId = stringToJSON.get("id").toString().toInt()
        UserApiBuilder.userRetro().getUserById(getId).enqueue(object: Callback<User>{
            override fun onFailure(call: Call<User>, t: Throwable) {
                userToken.value = null
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful){
                    currentUser.value = response.body()
                } else {
                    userToken.value = null
                }
            }

        })
    }

}


