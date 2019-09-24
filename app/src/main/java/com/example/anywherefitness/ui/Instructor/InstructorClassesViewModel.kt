package com.example.anywherefitness.ui.Instructor

import android.content.Context
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.anywherefitness.FitnessClassAdapter
import com.example.anywherefitness.model.FitnessClass

class InstructorClassesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

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