package com.example.anywherefitness.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.anywherefitness.Api.UserApiBuilder
import com.example.anywherefitness.R
import com.example.anywherefitness.model.User
import com.example.anywherefitness.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var id = 0

        val registerModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        val observer = Observer<List<User>> {id = it.size + 1}
        registerModel.userList.observe(this, observer)

        btn_register.setOnClickListener {
            val username = et_register_username.text.toString()
            val password = et_register_password.text.toString()
            val confirmPassword = et_confirm_password.text.toString()
            val isInstructor = checkbox_instructor.isChecked

            val user = User(username, password, isInstructor, id =  id)

            if (password == confirmPassword && username.isNotEmpty() && password.isNotEmpty()){
                UserApiBuilder.userRetro().addUser(user).enqueue(object: Callback<User>{
                    override fun onFailure(call: Call<User>, t: Throwable) {
                        println(t)
                    }

                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        if (response.isSuccessful){
                            println(response.body()?.username)
                            println(response.body()?.password)
                        } else {
                            println(response)
                        }
                    }

                })
            }


        }

    }
}
