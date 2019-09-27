package com.example.anywherefitness.ui.Instructor

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.anywherefitness.Api.UserApiBuilder
import com.example.anywherefitness.App
import com.example.anywherefitness.database.UserRepo
import com.example.anywherefitness.model.FitnessClass
import com.example.anywherefitness.model.User
import kotlinx.android.synthetic.main.fragment_classes_instructor.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InstructorClassesViewModel(application: Application) : AndroidViewModel(application) {

    var repo = UserRepo(application)
    var instructorFitnessClassList: MutableList<FitnessClass> = repo.getAllFitnessClasss()

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }

    
    val text: LiveData<String> = _text

    fun delete(fitnessClass: FitnessClass) {
        App.repo?.delete(fitnessClass)
    }

    fun deleteClassById(classId: Int) {
        repo.deleteClassById(classId)
    }

    fun insert(fitnessClass: FitnessClass) {
        repo.insert(fitnessClass)
    }

    fun deleteAllClasses() {
        repo.deleteAllClasses()
    }

    fun getClassList(getSavedToken: String?, userId: Int) {
        UserApiBuilder.userRetro().getAllClasses(getSavedToken!!)
            .enqueue(object : Callback<List<FitnessClass>> {
                override fun onFailure(call: Call<List<FitnessClass>>, t: Throwable) {
                    Log.i("BIGBRAIN", "onFailure $t")
                }

                override fun onResponse(
                    call: Call<List<FitnessClass>>,
                    response: Response<List<FitnessClass>>
                ) {
                    if (response.isSuccessful) {
                        Log.i("BIGBRAIN", "onReponse ${response.body()?.size}")
                        deleteAllClasses()
                        instructorFitnessClassList = response.body()!!.toMutableList()
                        for (i in 0 until instructorFitnessClassList.size) {
                            if (instructorFitnessClassList[i].instructor_id == userId) {
                                insert(instructorFitnessClassList[i])
                            }
                        }
                        instructorFitnessClassList = getAllClasses()
                        //rv_instructor_classes.adapter?.notifyDataSetChanged()
                    } else {
                        Log.i("BIGBRAIN", "onReponse failure $response")
                    }
                }

            })
    }

    //TODO: add api call to get all classes for this instructor
    /*fun getAllInstructorClasses(): MutableList<FitnessClass> {
        var fitnessClassInstructorList = mutableListOf<FitnessClass>()
        UserApiBuilder.userRetro().getAllClasses().enqueue(object : Callback<List<FitnessClass>> {
            override fun onFailure(call: Call<List<FitnessClass>>, t: Throwable) {
                Log.i("BIGBRAIN", "onFailure $t")
            }

            override fun onResponse(
                call: Call<List<FitnessClass>>,
                response: Response<List<FitnessClass>>
            ) {
                if (response.isSuccessful) {
                    Log.i("BIGBRAIN", "onReponse ${response.body()?.size}")
                    deleteAllClasses()
                    fitnessClassInstructorList = response.body()!!.toMutableList()
                    for (i in 0 until fitnessClassInstructorList.size) {
                        if (fitnessClassInstructorList[i].instructor_id == 11) {
                            insert(fitnessClassInstructorList[i])
                        }
                    }
                    fitnessClassInstructorList = getAllClasses()
                    //rv_instructor_classes.adapter?.notifyDataSetChanged()
                } else {
                    Log.i("BIGBRAIN", "onReponse failure $response")
                }
            }

        })
        return
    }*/

    fun getAllClasses(): MutableList<FitnessClass> {
        val instructorFitnessClassList: MutableList<FitnessClass> = repo.getAllFitnessClasss()
        return instructorFitnessClassList
    }

    fun getUser(): LiveData<User>?{
        return App.repo?.getUser()
    }
}