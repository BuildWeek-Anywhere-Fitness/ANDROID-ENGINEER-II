package com.example.anywherefitness.ui.Instructor

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.anywherefitness.R

class InstructorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructor)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment_instructor)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_classes_instructor, R.id.navigation_create_class_instructor, R.id.navigation_punchcard_instructor
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
                R.id.navigation_punchcard_instructor -> {
                    selectedFragment = PunchCardFragment()
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
}