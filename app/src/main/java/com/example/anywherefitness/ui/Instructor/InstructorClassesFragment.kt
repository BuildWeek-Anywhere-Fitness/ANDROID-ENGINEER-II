package com.example.anywherefitness.ui.Instructor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.anywherefitness.R

class InstructorClassesFragment : Fragment() {

    private lateinit var instructorClassesViewModel: InstructorClassesViewModel

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
}