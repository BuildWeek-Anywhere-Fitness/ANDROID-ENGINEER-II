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
import kotlinx.android.synthetic.main.fragment_find_classes.*

class FindClassesFragment : Fragment() {

    private lateinit var findClassesViewModel: FindClassesViewModel
    private lateinit var fitnessClassList: MutableList<FitnessClass>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        findClassesViewModel =
            ViewModelProviders.of(this).get(FindClassesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_find_classes, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        findClassesViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_search_classes.setOnClickListener {
            fitnessClassList = findClassesViewModel.searchClasses()
            rv_find_classes.adapter?.notifyDataSetChanged()
        }

        findClassesViewModel.setupRecycler(context,
            fitnessClassList,
            rv_find_classes,
            findClassesViewModel.repo)
    }
}