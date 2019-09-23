package com.example.anywherefitness.database

import android.content.Context
import android.os.AsyncTask
import com.example.anywherefitness.model.FitnessClass

class UserRepo (context: Context) {

    private var userDao: UserDao
    lateinit var fitnessClassList: List<FitnessClass>

    init {
        val database: UserDatabase = UserDatabase.getInstance(context)!!
        userDao = database.userDao()
    }

    fun insert(movie: FitnessClass) {
        InsertFitnessClassAsyncTask(userDao).execute(movie)
    }

    fun update(movie: FitnessClass) {
        UpdateFitnessClassAsyncTask(userDao).execute(movie)
    }

    fun delete(movie: FitnessClass) {
        DeleteFitnessClassAsyncTask(userDao).execute(movie)
    }

    fun getAllFitnessClasss(): List<FitnessClass> {
        return AllFitnessClassAsyncTask(userDao).execute().get()
    }

    companion object {

        private class InsertFitnessClassAsyncTask(userDao: UserDao) : AsyncTask<FitnessClass, Unit, Unit>() {
            val UserDao = userDao

            override fun doInBackground(vararg p0: FitnessClass?) {
                UserDao.insert(p0[0]!!)
            }
        }

        private class UpdateFitnessClassAsyncTask(userDao: UserDao) : AsyncTask<FitnessClass, Unit, Unit>() {
            val UserDao = userDao

            override fun doInBackground(vararg p0: FitnessClass?) {
                UserDao.update(p0[0]!!)
            }
        }

        private class DeleteFitnessClassAsyncTask(userDao: UserDao) : AsyncTask<FitnessClass, Unit, Unit>() {
            val UserDao = userDao

            override fun doInBackground(vararg p0: FitnessClass?) {
                UserDao.delete(p0[0]!!)
            }
        }

        private class AllFitnessClassAsyncTask(userDao: UserDao) : AsyncTask<FitnessClass, Unit, List<FitnessClass>>() {
            val UserDao = userDao

            override fun doInBackground(vararg p0: FitnessClass?): List<FitnessClass> {
                val movieList = UserDao.getAllFitnessClasses()
                return movieList
            }

            override fun onPostExecute(result: List<FitnessClass>?) {
                super.onPostExecute(result)
                val fitnessClassList = result

            }
        }
    }
}