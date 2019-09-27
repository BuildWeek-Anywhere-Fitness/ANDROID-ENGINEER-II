package com.example.anywherefitness.ui.Instructor

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.anywherefitness.R
import com.example.anywherefitness.model.User
import com.example.anywherefitness.ui.LoginActivity
import com.example.anywherefitness.ui.StartUpActivity
import com.example.anywherefitness.ui.client.MyClassesFragment
import com.example.anywherefitness.viewmodel.LoginViewModel

class InstructorActivity : AppCompatActivity() {

    companion object {
        val INSTRUCTOR_USER = "key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructor)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment_instructor)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_classes_instructor, R.id.navigation_create_class_instructor
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navView.setOnNavigationItemSelectedListener {
            var selectedFragment: Fragment? = null
            when (it.itemId) {
                R.id.navigation_classes_instructor -> {
                    selectedFragment = InstructorClassesFragment()
                }
                R.id.navigation_create_class_instructor -> {
                    selectedFragment = CreateClassesFragment()
                }
            }
            selectedFragment?.let { it1 ->
                /*val fragmentBundle = Bundle()
                fragmentBundle.putSerializable(
                    //FRAGMENT_KEY,
                    //put extras if needed here
                )*/
                //selectedFragment.arguments = fragmentBundle
                supportFragmentManager.beginTransaction().replace(
                    R.id.nav_host_fragment_instructor,
                    it1
                ).commit()
            }
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.log_out_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val saveToken = getSharedPreferences(LoginActivity.SAVE_TOKEN, Context.MODE_PRIVATE)
        saveToken.edit{
            clear()
        }
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
        return super.onOptionsItemSelected(item)
    }
}
