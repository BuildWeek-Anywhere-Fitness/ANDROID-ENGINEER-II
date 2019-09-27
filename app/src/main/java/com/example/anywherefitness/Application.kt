package com.example.anywherefitness

import android.app.Application
import com.example.anywherefitness.database.UserRepo

class App: Application(){

    companion object{
        var repo: UserRepo? = null
    }

    override fun onCreate() {
        super.onCreate()
        repo = UserRepo(this)
    }
}