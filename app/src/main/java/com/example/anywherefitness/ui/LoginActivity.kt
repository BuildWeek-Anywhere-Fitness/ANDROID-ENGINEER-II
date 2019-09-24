package com.example.anywherefitness.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.anywherefitness.Api.UserApiBuilder
import com.example.anywherefitness.viewmodel.LoginViewModel
import com.example.anywherefitness.R
import com.example.anywherefitness.model.User
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val loginModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        //TODO: make observer work with login token
        val tokenObserver = Observer<String> {
            println(it)
        }

        btn_register.setOnClickListener {
            val registerIntent = Intent(this, RegisterActivity::class.java)
            startActivity(registerIntent)
        }

        btn_login.setOnClickListener {
            val user = User(et_username.text.toString(), et_password.text.toString())
            loginModel.getToken(user)
            loginModel.userToken.observe(this, tokenObserver)

        }
    }
}
