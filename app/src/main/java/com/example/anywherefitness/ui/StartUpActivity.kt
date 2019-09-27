package com.example.anywherefitness.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.anywherefitness.App
import com.example.anywherefitness.R
import com.example.anywherefitness.database.UserRepo
import com.example.anywherefitness.model.User
import com.example.anywherefitness.ui.Instructor.InstructorActivity
import com.example.anywherefitness.ui.LoginActivity.Companion.GET_SAVE_TOKEN
import com.example.anywherefitness.ui.LoginActivity.Companion.SAVE_TOKEN
import com.example.anywherefitness.viewmodel.LoginViewModel

class StartUpActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_up)
        val saveToken = getSharedPreferences(SAVE_TOKEN, Context.MODE_PRIVATE)
        val getSavedToken = saveToken.getString(GET_SAVE_TOKEN, "default")
        val loginModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        val userObserver = Observer<User> {

            if (it != null){
                loginModel.setUser(it)

                val isInstructor = when(it.instructor){
                    1 -> true
                    else -> false
                }

                val intent = when(isInstructor){
                    true -> {
                        Intent(this, InstructorActivity::class.java)
                    }
                    false -> {
                        Intent(this, ClientActivity::class.java)
                    }
                }
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent)
                finish()
            }
            else {
                startActivity(Intent(this, LoginActivity::class.java))
            }

        }

        if (getSavedToken != "default") {
            getSavedToken?.let {
                loginModel.getCurrentUser(it)
            }
            loginModel.currentUser.observe(this, userObserver)
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
        }


    }
}