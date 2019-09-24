package com.example.anywherefitness.ui.client

import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.anywherefitness.FitnessClassAdapter
import com.example.anywherefitness.model.FitnessClass

class FindClassesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Find Classes Fragment"
    }
    val text: LiveData<String> = _text

    fun setupRecycler(context: Context?,
                      fitnessClassList: MutableList<FitnessClass>,
                      clickListener: View.OnClickListener,
                      longClickListener: View.OnLongClickListener?,
                      recyclerView: RecyclerView
    ) {
        recyclerView.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(context)
            adapter = FitnessClassAdapter(fitnessClassList, clickListener, longClickListener)
        }
    }

    fun setClickListner(context: Context?): View.OnClickListener {
        val clickListener = View.OnClickListener() {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Register for Class")
            builder.setMessage("Are you sure you want to register for this class?")
            builder.setPositiveButton("YES"){dialog, which ->
                //add appropriate database calls here
                Toast.makeText(context, "Registered for class", Toast.LENGTH_LONG).show()
            }

            builder.setNegativeButton("NO"){_, _ ->}

            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
        return clickListener
    }
}

//need a recycler view, click listener will sign the user up for the class with an alert dialog, longclick listener not needed
