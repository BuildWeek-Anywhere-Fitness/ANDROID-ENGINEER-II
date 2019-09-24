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

        myFitnessClassList = mutableListOf()

        myFitnessClassList.add(FitnessClass("test", "test", "test", 43, 10, "test", 10, 15))
        myFitnessClassList.add(FitnessClass("test2", "test2", "test2", 43, 10, "test2", 10, 15))
        myFitnessClassList.add(FitnessClass("test3", "test3", "test3", 43, 10, "test3", 10, 15))


        myClassesViewModel.setupRecycler(context,
            myFitnessClassList,
            myClassesViewModel.setClickListner(context),
            null,
            rv_my_classes)

    }
}