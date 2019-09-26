package com.example.anywherefitness.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.anywherefitness.model.FitnessClass
import com.example.anywherefitness.model.User

@Database(entities = [FitnessClass::class], exportSchema = true, version = 5)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private var instance: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase? {

            if (instance == null) {
                synchronized(UserDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java, "user_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance //as MovieDatabase
        }
    }
}