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

    fun insert(fitnessClass: FitnessClass) {
        InsertFitnessClassAsyncTask(userDao).execute(fitnessClass)
    }

    fun update(fitnessClass: FitnessClass) {
        UpdateFitnessClassAsyncTask(userDao).execute(fitnessClass)
    }

    fun delete(fitnessClass: FitnessClass) {
        DeleteFitnessClassAsyncTask(userDao).execute(fitnessClass)
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
                val fitnessClassList = UserDao.getAllFitnessClasses()
                return fitnessClassList
            }

            override fun onPostExecute(result: List<FitnessClass>?) {
                super.onPostExecute(result)
                val fitnessClassList = result

            }
        }
    }
}