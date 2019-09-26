package com.example.anywherefitness.ui.client

import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.anywherefitness.Api.UserApiBuilder
import com.example.anywherefitness.ClientFitnessClassAdapter
import com.example.anywherefitness.FitnessClassAdapter
import com.example.anywherefitness.database.UserRepo
import com.example.anywherefitness.model.FitnessClass

class MyClassesViewModel(application: Application) : AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "My Classes Fragment"
    }
    val text: LiveData<String> = _text
    val repo = UserRepo(application)

    //TODO: click listener in adapter will remove class from database, will need to sync with api at somepoint as well
    fun setupRecycler(context: Context?,
                      fitnessClassList: MutableList<FitnessClass>,
                      recyclerView: RecyclerView,
                      repo: UserRepo
    ) {
        recyclerView.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(context)
            adapter = ClientFitnessClassAdapter(fitnessClassList, repo)
        }
    }

    fun getAllClasses(): MutableList<FitnessClass> {
        var instructorFitnessClassList: MutableList<FitnessClass> = repo.getAllFitnessClasss()
        return instructorFitnessClassList
    }


}