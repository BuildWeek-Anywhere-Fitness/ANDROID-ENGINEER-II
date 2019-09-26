package com.example.anywherefitness.ui

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.anywherefitness.R
import com.example.anywherefitness.model.User
import com.example.anywherefitness.ui.client.FindClassesFragment
import com.example.anywherefitness.ui.client.MyClassesFragment

class ClientActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val myClassesFragment = MyClassesFragment()
        val user = intent.getSerializableExtra(StartUpActivity.USER) as User
        val bundle = Bundle()
        bundle.putSerializable("key", user)
        myClassesFragment.arguments = bundle

        val navController = findNavController(R.id.nav_host_fragment_client)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_my_classes,
                R.id.navigation_find_classes
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navView.setOnNavigationItemSelectedListener {
            var selectedFragment: Fragment? = null
            when (it.itemId) {
                R.id.navigation_find_classes -> {
                    selectedFragment = FindClassesFragment()
                }
                R.id.navigation_my_classes -> {
                    selectedFragment = MyClassesFragment()
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
                    R.id.nav_host_fragment_client,
                    it1
                ).commit()
            }
            true
        }

    }
}
