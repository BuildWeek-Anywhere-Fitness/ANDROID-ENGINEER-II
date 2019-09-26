package com.example.anywherefitness.ui.client

import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.anywherefitness.Api.UserApiBuilder
import com.example.anywherefitness.database.UserRepo
import com.example.anywherefitness.model.FitnessClass
import com.example.anywherefitness.model.FitnessClassResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FindClassesViewModel(application: Application) : AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "Find Classes Fragment"
    }
    val text: LiveData<String> = _text

    var resultList = MutableLiveData<List<FitnessClass>>()


    init {
        UserApiBuilder.userRetro().getAllClasses().enqueue(object: Callback<List<FitnessClass>> {

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


}

