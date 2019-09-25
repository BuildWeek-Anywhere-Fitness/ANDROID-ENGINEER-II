package com.example.anywherefitness.ui.client

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.anywherefitness.R
import com.example.anywherefitness.model.FitnessClass
import kotlinx.android.synthetic.main.fragment_my_classes.*

class MyClassesFragment : Fragment() {

    private lateinit var myClassesViewModel: MyClassesViewModel
    private lateinit var myFitnessClassList: MutableList<FitnessClass>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myClassesViewModel =
            ViewModelProviders.of(this).get(MyClassesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_my_classes, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        myClassesViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myFitnessClassList = myClassesViewModel.getAllClasses()

        myClassesViewModel.setupRecycler(context,
            myFitnessClassList,
            rv_my_classes,
            myClassesViewModel.repo)

    }
}