package com.example.anywherefitness.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.anywherefitness.R
import com.example.anywherefitness.model.User
import com.example.anywherefitness.ui.Instructor.InstructorActivity
import com.example.anywherefitness.ui.client.ClientActivity
import com.example.anywherefitness.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_walkthrough.*

class WalkthroughActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_walkthrough)
        title = "Walkthrough"

        val waltthroughlModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        var user: User? = null
        val userObserver = Observer<User> {
            user = it
        }

        waltthroughlModel.getUser()?.observe(this, userObserver)
        userObserver.onChanged(waltthroughlModel.getUser()?.value)

        val intent = if (user!!.instructor) {
            tv_walkthrough.text = "Create a class for clients to join"
            Intent(this, InstructorActivity::class.java)
        } else {
            tv_walkthrough.text = "Join a class by clicking on it and the same to delete it from your saved"
            Intent(this, ClientActivity::class.java)
        }

        btn_skip.setOnClickListener {
            startActivity(intent)
            finish()
        }
    }
}