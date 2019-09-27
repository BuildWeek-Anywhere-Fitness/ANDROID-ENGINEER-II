package com.example.anywherefitness.ui


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.edit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.anywherefitness.viewmodel.LoginViewModel
import com.example.anywherefitness.R
import com.example.anywherefitness.model.User
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    companion object {
        val REGISTER_CODE = 210
        val SAVE_TOKEN = "saved preference"
        val GET_SAVE_TOKEN = "token"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        title = "Login"
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
            } else{
                Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_SHORT).show()
            }
        }

        val userObserver = Observer<User> {
            if (it != null){
                val intent = Intent(this, WalkthroughActivity::class.java)
                loginModel.setUser(it)
                startActivity(intent)
                finish()
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
            loginModel.currentUser.observe(this, userObserver)

        }
    }
}