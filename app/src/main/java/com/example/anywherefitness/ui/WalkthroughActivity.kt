package com.example.anywherefitness.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.anywherefitness.R
import com.example.anywherefitness.model.User
import com.example.anywherefitness.ui.Instructor.InstructorActivity
import com.example.anywherefitness.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_walkthrough.*

class WalkthroughActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_walkthrough)

        val waltthroughlModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        var isInstructor = false
        val userObserver = Observer<User> {
            isInstructor = when(it.instructor){
                1 -> true
                else -> false
            } }
        waltthroughlModel.getUser()?.observe(this, userObserver)

        val intent = if (isInstructor) {
            Intent(this, InstructorActivity::class.java)
        } else {
            Intent(this, ClientActivity::class.java)
        }

        btn_skip.setOnClickListener {
            startActivity(intent)
            finish()
        }
    }
}