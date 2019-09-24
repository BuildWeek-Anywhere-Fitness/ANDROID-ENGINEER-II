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
        val observer = Observer<List<User>> {
            val userName = et_username.text.toString()
            for (user in it){
                if (user.username == userName){
                    println(userName)
                    break
                } else {
                    println("failed")
                    break
                }
            }
        }

        btn_register.setOnClickListener {
            observer.onChanged(loginModel.userList.value)
            loginModel.userList.observe(this, observer)

        }

        btn_login.setOnClickListener {
            val intent = Intent(this, WalkthroughActivity::class.java)
            startActivity(intent)
        }
    }
}
