package com.example.anywherefitness.ui

import android.content.Context
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

        val loginModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        val saveToken = getSharedPreferences(StartUpActivity.SAVE_TOKEN, Context.MODE_PRIVATE)
        val getSavedToken = saveToken.getString(StartUpActivity.GET_SAVE_TOKEN, "default")

        getSavedToken?.let {
            if (it != "default"){
                loginModel.getCurrentUser(it)
            }
        }

        val userObserver = Observer<User> {
            val intent = if (it.instructor) {
                Intent(this, InstructorActivity::class.java)
            } else {
                Intent(this, ClientActivity::class.java)
            }
            startActivity(intent)
            finish()
        }




        btn_skip.setOnClickListener {
            loginModel.currentUser.observe(this, userObserver)
        }
    }
}
