package com.example.anywherefitness.ui.Instructor

import android.app.Application
import android.content.Context
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.anywherefitness.FitnessClassAdapter
import com.example.anywherefitness.database.UserRepo
import com.example.anywherefitness.model.FitnessClass

class InstructorClassesViewModel(application: Application) : AndroidViewModel(application) {

    private var repo = UserRepo(application)
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

    fun getAllClasses(): MutableList<FitnessClass> {
        var instructorFitnessClassList: MutableList<FitnessClass> = repo.getAllFitnessClasss()
        return instructorFitnessClassList
    }

    fun setupRecycler(context: Context?,
                      fitnessClassList: MutableList<FitnessClass>,
                      clickListener: View.OnClickListener,
                      longClickListener: View.OnLongClickListener,
                      recyclerView: RecyclerView) {
        recyclerView.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(context)
            adapter = FitnessClassAdapter(fitnessClassList, clickListener, longClickListener)
        }
    }


}