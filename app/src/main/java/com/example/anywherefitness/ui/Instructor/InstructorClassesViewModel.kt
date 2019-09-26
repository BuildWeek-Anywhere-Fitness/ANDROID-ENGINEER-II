package com.example.anywherefitness.ui.Instructor

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.anywherefitness.database.UserRepo
import com.example.anywherefitness.model.FitnessClass

class InstructorClassesViewModel(application: Application) : AndroidViewModel(application) {

    var repo = UserRepo(application)
    var instructorFitnessClassList: MutableList<FitnessClass> = repo.getAllFitnessClasss()

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }

    
    val text: LiveData<String> = _text

    fun delete(fitnessClass: FitnessClass) {
        repo.delete(fitnessClass)
    }

    fun deleteClassById(classId: Int) {
        repo.deleteClassById(classId)
    }

    //TODO: add api call to get all classes for this instructor

    fun getAllClasses(): MutableList<FitnessClass> {
        var instructorFitnessClassList: MutableList<FitnessClass> = repo.getAllFitnessClasss()
        return instructorFitnessClassList
    }
}