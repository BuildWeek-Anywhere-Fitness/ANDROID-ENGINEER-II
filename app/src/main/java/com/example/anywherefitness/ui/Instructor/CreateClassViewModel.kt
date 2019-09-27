package com.example.anywherefitness.ui.Instructor

import android.app.Application
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.anywherefitness.App
import com.example.anywherefitness.database.UserRepo
import com.example.anywherefitness.model.FitnessClass
import com.example.anywherefitness.model.User

class CreateClassViewModel(application: Application) : AndroidViewModel(application) {

    private var repo: UserRepo = UserRepo(application)

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    //TODO: add sync with api when new class is created, can likely just do it when create class button is clicked because it won't be done
    //as frequently
    //TODO: fix things after changing class constructor to match api
    fun createClass(etName: EditText,
                    etType: EditText,
                    etTime: EditText,
                    etDuration: EditText,
                    etIntensity: EditText,
                    etLocation: EditText,
                    etAttendees: EditText,
                    etSize: EditText): FitnessClass {

        //can't convert et.text to Int in constructor
        val duration = etDuration.text.toString()
        //val durationInt = duration.toInt()
        val intensity = etIntensity.text.toString()
        //val intensityInt = intensity.toInt()
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

    fun insert(newClass: FitnessClass) {
        repo.insert(newClass)
    }

    fun saveClass(newClass: FitnessClass) {
        //make appropriate db calls and ui updates here
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