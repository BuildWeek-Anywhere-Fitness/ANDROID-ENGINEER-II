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
import kotlinx.android.synthetic.main.fragment_create_class_instructor.*

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_save_class.setOnClickListener{
            val newClass = createClassViewModel.createClass(
                et_class_name,
                et_class_type,
                et_class_time,
                et_class_duration,
                et_class_intensity,
                et_class_location,
                et_class_attendees,
                et_class_size)

            createClassViewModel.saveClass(newClass)
            Toast.makeText(context, "new class created ${newClass.name} ${newClass.numberOfAttendees}", Toast.LENGTH_LONG).show()
        }
    }
}