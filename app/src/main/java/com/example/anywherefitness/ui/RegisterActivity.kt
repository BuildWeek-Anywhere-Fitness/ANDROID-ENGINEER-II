package com.example.anywherefitness.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.anywherefitness.R
import com.example.anywherefitness.model.CreateUser
import com.example.anywherefitness.model.User
import com.example.anywherefitness.viewmodel.LoginViewModel
import com.example.anywherefitness.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)



        val registerViewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)
        val observer = Observer<Boolean>{
            if (it){
                setResult(Activity.RESULT_OK)
                finish()
            }
        }


        btn_register.setOnClickListener {
            val username = et_register_username.text.toString()
            val password = et_register_password.text.toString()
            val confirmPassword = et_confirm_password.text.toString()
            val isInstructor = checkbox_instructor.isChecked

            val user = CreateUser(username, password, isInstructor)

            if (password == confirmPassword && username.isNotEmpty() && password.isNotEmpty()){
                registerViewModel.createUser(user)
                registerViewModel.isSuccessful.observe(this, observer)
            }


        }

    }
}
