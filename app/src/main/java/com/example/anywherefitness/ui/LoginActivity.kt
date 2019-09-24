package com.example.anywherefitness.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.anywherefitness.Api.UserApiBuilder
import com.example.anywherefitness.viewmodel.LoginViewModel
import com.example.anywherefitness.R
import com.example.anywherefitness.model.User
import com.example.anywherefitness.ui.Instructor.InstructorActivity
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    companion object {
        val REGISTER_CODE = 210
        val SAVE_TOKEN = "saved preference"
        val GET_SAVE_TOKEN = "token"
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val saveToken = getSharedPreferences(SAVE_TOKEN, Context.MODE_PRIVATE)

        val getSavedToken = saveToken.getString(GET_SAVE_TOKEN, "default")
        println(getSavedToken)


        /*if (save token works & have seen walk through){
            when(User.isInstructor){
                true -> startActivity(Intent(this, InstructorActivity::class.java))
                false -> startActivity(Intent(this, ClientActivity::class.java))
            }
        } else if (save toke works & ! have seen walk through){
            startActivity(Intent(this, WalkthroughActivity::class.java))
        } else {
            logIn()
        }*/

        val loginModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        //TODO: make observer work with login token
        val tokenObserver = Observer<String> {
            println(it)
            if (it != null) {
                saveToken.edit {
                    this.putString(GET_SAVE_TOKEN, it)
                    this.commit()
                }
            }
        }

        btn_register.setOnClickListener {
            val registerIntent = Intent(this, RegisterActivity::class.java)
            startActivityForResult(registerIntent, REGISTER_CODE)
        }

        btn_login.setOnClickListener {
            val user = User(et_username.text.toString(), et_password.text.toString())
            loginModel.getToken(user)
            loginModel.userToken.observe(this, tokenObserver)

        }
    }

}
