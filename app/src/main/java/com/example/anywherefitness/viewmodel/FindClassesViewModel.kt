package com.example.anywherefitness.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.anywherefitness.Api.UserApiBuilder
import com.example.anywherefitness.model.FitnessClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FindClassesViewModel(application: Application) : AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "Search for Classes"
    }
    val text: LiveData<String> = _text

    var resultList = MutableLiveData<List<FitnessClass>>()

    fun getList(token: String){
        UserApiBuilder.userRetro().getAllClasses(token).enqueue(object: Callback<List<FitnessClass>> {

            override fun onFailure(call: Call<List<FitnessClass>>, t: Throwable) {
                Log.i("DEBUG", t.toString())
            }

            override fun onResponse(call: Call<List<FitnessClass>>, response: Response<List<FitnessClass>>) {
                if (response.isSuccessful) {
                    resultList.value = response.body()
                    Log.i("DEBUGGGG", "response ${response.body()}")
                } else {
                    Log.i("DEBUGGGG", "response $response")
                }
            }

        })
    }

     /*fun getUser(): LiveData<User>?{
         return App.repo?.getUser()
     }*/


}

