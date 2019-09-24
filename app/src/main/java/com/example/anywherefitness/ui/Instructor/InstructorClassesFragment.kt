package com.example.anywherefitness.ui.Instructor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.anywherefitness.R
import com.example.anywherefitness.model.FitnessClass
import kotlinx.android.synthetic.main.fragment_classes_instructor.*

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //fitnessClassList.add(FitnessClass("test", "test", "test", 43, 10, "test", 10, 15))
        //fitnessClassList.add(FitnessClass("test2", "test2", "test2", 43, 10, "test2", 10, 15))
        //fitnessClassList.add(FitnessClass("test3", "test3", "test3", 43, 10, "test3", 10, 15))

        instructorClassesViewModel.setupRecycler(context, fitnessClassList, clickListener, longClickListener, rv_instructor_classes)
    }

    val clickListener =  View.OnClickListener {
        val color = resources.getColor(R.color.colorAccent)
        it.setBackgroundColor(color)
    }

    val longClickListener = View.OnLongClickListener {
        Toast.makeText(context, "Long Click", Toast.LENGTH_LONG).show()
        true
    }

    val fitnessClassList = mutableListOf<FitnessClass>()

}