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

class PunchCardFragment : Fragment() {

    private lateinit var punchCardViewModel: PunchCardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        punchCardViewModel =
            ViewModelProviders.of(this).get(PunchCardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_punchcard_instructor, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        punchCardViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}