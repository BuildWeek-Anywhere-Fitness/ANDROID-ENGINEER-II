package com.example.anywherefitness.ui.Instructor

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anywherefitness.FitnessClassAdapter
import com.example.anywherefitness.R
import com.example.anywherefitness.model.FitnessClass
import kotlinx.android.synthetic.main.fitness_class_list_view.view.*
import kotlinx.android.synthetic.main.fragment_classes_instructor.*

class InstructorClassesFragment : Fragment() {

    private lateinit var instructorClassesViewModel: InstructorClassesViewModel
    private lateinit var fitnessClassList : MutableList<FitnessClass>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        instructorClassesViewModel =
            ViewModelProviders.of(this).get(InstructorClassesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_classes_instructor, container, false)
        val textView: TextView = root.findViewById(R.id.text_classes_instructor)
        instructorClassesViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fitnessClassList = instructorClassesViewModel.getAllClasses()

        instructorClassesViewModel.setupRecycler(
            context,
            fitnessClassList,
            rv_instructor_classes,
            instructorClassesViewModel.repo
        )
    }
}