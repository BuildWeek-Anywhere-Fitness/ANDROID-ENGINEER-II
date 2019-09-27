package com.example.anywherefitness.ui.client

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.anywherefitness.database.UserRepo
import com.example.anywherefitness.model.FitnessClass

class MyClassesViewModel(application: Application) : AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "My Classes"
    }
    val text: LiveData<String> = _text
    val repo = UserRepo(application)

    //TODO: click listener in adapter will remove class from database, will need to sync with api at somepoint as well

    fun getAllClasses(): MutableList<FitnessClass> {
        var instructorFitnessClassList: MutableList<FitnessClass> = repo.getAllFitnessClasss()
        return instructorFitnessClassList
    }


}