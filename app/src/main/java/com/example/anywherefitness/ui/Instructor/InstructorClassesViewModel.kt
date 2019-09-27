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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InstructorClassesViewModel(application: Application) : AndroidViewModel(application) {

    var repo = UserRepo(application)
    var instructorFitnessClassList: MutableList<FitnessClass> = repo.getAllFitnessClasss()
    val list = MutableLiveData<List<FitnessClass>>()

    private val _text = MutableLiveData<String>().apply {
        value = "My Classes"
    }
    
    val text: LiveData<String> = _text

    fun insert(fitnessClass: FitnessClass) {
        App.repo?.insert(fitnessClass)
    }

    fun deleteAllClasses() {
        App.repo?.deleteAllClasses()
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
                        list.value = instructorFitnessClassList
                    } else {
                        Log.i("BIGBRAIN", "onReponse failure $response")
                    }
                }
            })
    }

    fun deleteClass(id: Int, getSavedToken: String?, position: Int) {
        UserApiBuilder.userRetro().deleteClass(getSavedToken!!, id)
            .enqueue(object : Callback<Void> {
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.i("BIGBRAIN", "onFailure $t")
                }

                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        App.repo?.deleteClassById(id)
                        instructorFitnessClassList?.removeAt(position)
                        list.value = instructorFitnessClassList
                        Log.i("BIGBRAIN", "reponse success $response")
                    } else {
                        Log.i("BIGBRAIN", "reponse failed $response")
                    }
                }
            })
    }

    fun getAllClasses(): MutableList<FitnessClass> {
        val instructorFitnessClassList: MutableList<FitnessClass> = App.repo?.getAllFitnessClasss() ?: mutableListOf()
        return instructorFitnessClassList
    }

    fun getUser(): LiveData<User>?{
        return App.repo?.getUser()
    }
}