package com.example.anywherefitness.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.anywherefitness.R
import com.example.anywherefitness.ui.Instructor.InstructorActivity
import kotlinx.android.synthetic.main.activity_walkthrough.*

class WalkthroughActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_walkthrough)

        //check if instructor or client and if they have gone through walkthrough before
        //if client set text to client walkthrough and vice versa

        btn_skip.setOnClickListener {
            val intent = Intent(this, InstructorActivity::class.java)
            startActivity(intent)
        }
    }
}
