package com.example.anywherefitness.ui.client

import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.anywherefitness.FitnessClassAdapter
import com.example.anywherefitness.SearchFitnessClassAdapter
import com.example.anywherefitness.database.UserRepo
import com.example.anywherefitness.model.FitnessClass

class FindClassesViewModel(application: Application) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Find Classes Fragment"
    }
    val text: LiveData<String> = _text
    val repo = UserRepo(application)

    //TODO: figure out when/how to sync with api
    //on click listener in the adapter will add class to database, will also need to add to api, but we probably don't want to do that
    //everytime, so somewhere we will need to do a sync with api
    fun setupRecycler(context: Context?,
                      fitnessClassList: MutableList<FitnessClass>,
                      recyclerView: RecyclerView,
                      repo: UserRepo
    ) {
        recyclerView.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(context)
            adapter = SearchFitnessClassAdapter(fitnessClassList, repo)
        }
    }

    fun searchClasses(): MutableList<FitnessClass> {
        //TODO: api call to search classes here and return list of fitnessclass
        //possibly do somekind of check to not show classes client is already signed up for
        val fitnessClassResultList = mutableListOf<FitnessClass>()
        return fitnessClassResultList
    }
}

