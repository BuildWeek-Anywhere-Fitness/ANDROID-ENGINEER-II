package com.example.anywherefitness.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.anywherefitness.R
import com.example.anywherefitness.model.User
import com.example.anywherefitness.ui.Instructor.InstructorActivity
import kotlinx.android.synthetic.main.activity_walkthrough.*

class WalkthroughActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_walkthrough)

        val user = intent.getSerializableExtra(StartUpActivity.USER) as User

        val intent = if (user.instructor) {
            Intent(this, InstructorActivity::class.java)
        } else {
            Intent(this, ClientActivity::class.java)
        }

        btn_skip.setOnClickListener {
            intent.putExtra(StartUpActivity.USER, user)
            startActivity(intent)
            finish()
        }
    }
}