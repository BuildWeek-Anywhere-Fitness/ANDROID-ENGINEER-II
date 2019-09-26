package com.example.anywherefitness.ui


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.anywherefitness.viewmodel.LoginViewModel
import com.example.anywherefitness.R
import com.example.anywherefitness.model.User
import com.example.anywherefitness.ui.Instructor.InstructorActivity
import com.example.anywherefitness.ui.StartUpActivity.Companion.GET_SAVE_TOKEN
import com.example.anywherefitness.ui.StartUpActivity.Companion.REGISTER_CODE
import com.example.anywherefitness.ui.StartUpActivity.Companion.SAVE_TOKEN
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }


    override fun onResume() {
        super.onResume()

        val saveToken = getSharedPreferences(SAVE_TOKEN, Context.MODE_PRIVATE)

        val loginModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        val tokenObserver = Observer<String> {
            if (it != null) {
                saveToken.edit {
                    this.putString(GET_SAVE_TOKEN, it)
                    this.commit()
                }
                loginModel.getCurrentUser(it)
            }
        }

        val userObserver = Observer<User> {
            val intent = if (it != null){
                when(it.instructor){
                    true -> {
                        Intent(this, InstructorActivity::class.java)
                    }
                    false -> {
                        Intent(this, ClientActivity::class.java)
                    }
                }
            } else {
                Intent(this, LoginActivity::class.java)
            }
            intent.putExtra(StartUpActivity.USER, it)
            startActivity(intent)
            finish()
        }

        btn_register.setOnClickListener {
            val registerIntent = Intent(this, RegisterActivity::class.java)
            startActivityForResult(registerIntent, REGISTER_CODE)
        }

        btn_login.setOnClickListener {
            val user = User(et_username.text.toString(), et_password.text.toString())
            loginModel.getToken(user)
            loginModel.userToken.observe(this, tokenObserver)
            loginModel.currentUser.observe(this, userObserver)

        }
    }

}
