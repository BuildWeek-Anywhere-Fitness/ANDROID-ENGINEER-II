package com.example.anywherefitness.ui.Instructor

import android.app.Application
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.anywherefitness.database.UserRepo
import com.example.anywherefitness.model.FitnessClass

class CreateClassViewModel(application: Application) : AndroidViewModel(application) {

    private var repo: UserRepo = UserRepo(application)

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
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

        //can't convert et.text to Int in constructor
        val duration = etDuration.text.toString()
        val durationInt = duration.toInt()
        val intensity = etIntensity.text.toString()
        val intensityInt = intensity.toInt()
        val attendees = etAttendees.text.toString()
        val attendeesInt = attendees.toInt()
        val size = etSize.text.toString()
        val sizeInt = size.toInt()

        val newClass = FitnessClass(etName.text.toString(),
            etType.text.toString(),
            etTime.text.toString(),
            durationInt,
            intensityInt,
            etLocation.text.toString(),
            InstructorActivity.user.id,
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
}