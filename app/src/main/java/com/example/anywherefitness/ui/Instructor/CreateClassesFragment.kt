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

class CreateClassesFragment : Fragment() {

    private lateinit var createClassViewModel: CreateClassViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        createClassViewModel =
            ViewModelProviders.of(this).get(CreateClassViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_create_class_instructor, container, false)
        val textView: TextView = root.findViewById(R.id.text_create_class_instructor)
        createClassViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}