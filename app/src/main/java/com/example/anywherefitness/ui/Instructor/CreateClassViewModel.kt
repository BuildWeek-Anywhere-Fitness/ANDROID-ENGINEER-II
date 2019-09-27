package com.example.anywherefitness.ui.Instructor

import android.app.Application
import android.util.Log
import android.widget.EditText
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.anywherefitness.Api.UserApiBuilder
import com.example.anywherefitness.App
import com.example.anywherefitness.model.FitnessClass
import com.example.anywherefitness.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateClassViewModel(application: Application) : AndroidViewModel(application) {

    //private var repo: UserRepo = UserRepo(application)

    var responseString = MutableLiveData<String>()

    private val _text = MutableLiveData<String>().apply {
        value = "Create Class"
    }
    val text: LiveData<String> = _text

    fun createClass(etName: EditText,
                    etType: EditText,
                    etTime: EditText,
                    etDuration: EditText,
                    etIntensity: EditText,
                    etLocation: EditText,
                    etAttendees: EditText,
                    etSize: EditText): FitnessClass {

        val duration = etDuration.text.toString()
        val intensity = etIntensity.text.toString()
        val attendees = etAttendees.text.toString()
        val attendeesInt = attendees.toInt()
        val size = etSize.text.toString()
        val sizeInt = size.toInt()

        val newClass = FitnessClass(etName.text.toString(),
            etType.text.toString(),
            etTime.text.toString(),
            duration,
            intensity,
            etLocation.text.toString(),
            11,
            attendeesInt,
            sizeInt)

        return newClass
    }

    fun addClassToApi(getSavedToken: String?, newCreateClass: CreateFitnessClass){

        UserApiBuilder.userRetro().addClass(getSavedToken!!, newCreateClass).enqueue(object :
            Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.i("BIGBRAIN", "onFailure $t")
                responseString.value = "Failed to create class ${newCreateClass.name}"
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Log.i("BIGBRAIN", "success ${response.body()}")
                    responseString.value = "Successfully created new class ${newCreateClass.name}"
                } else {
                    Log.i("BIGBRAIN", "onFailure $response")
                    responseString.value = "Failed to create class ${newCreateClass.name}"
                }
            }
        })
    }


    fun getUser(): LiveData<User>?{
        return App.repo?.getUser()
    }

}

class CreateFitnessClass (
    val name: String,
    val type: String,
    val location: String,
    val duration: String,
    val intensity: String,
    val max_size: String,
    val starttime: String?,
    val instructor_id: Int,
    val id: Int?
)