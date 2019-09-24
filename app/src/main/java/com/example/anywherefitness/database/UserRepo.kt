package com.example.anywherefitness.database

import android.content.Context
import android.os.AsyncTask
import com.example.anywherefitness.model.FitnessClass

class UserRepo (context: Context) {

    private var userDao: UserDao
    lateinit var fitnessClassList: MutableList<FitnessClass>

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

    fun deleteClassById(classId: Int) {
        DeleteFitnessClassByIdAsyncTask(userDao).execute(classId)
    }

    fun getAllFitnessClasss(): MutableList<FitnessClass> {
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

        private class DeleteFitnessClassByIdAsyncTask(userDao: UserDao) : AsyncTask<Int, Unit, Unit>() {
            val UserDao = userDao

            override fun doInBackground(vararg p0: Int?) {
                UserDao.deleteClassById(p0[0]!!)
            }
        }

        private class AllFitnessClassAsyncTask(userDao: UserDao) : AsyncTask<FitnessClass, Unit, MutableList<FitnessClass>>() {
            val UserDao = userDao

            override fun doInBackground(vararg p0: FitnessClass?): MutableList<FitnessClass> {
                val fitnessClassList = UserDao.getAllFitnessClasses()
                return fitnessClassList
            }

            override fun onPostExecute(result: MutableList<FitnessClass>?) {
                super.onPostExecute(result)
                val fitnessClassList = result

            }
        }
    }
}