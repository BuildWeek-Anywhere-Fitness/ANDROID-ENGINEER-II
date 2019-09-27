package com.example.anywherefitness.ui.Instructor

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.anywherefitness.Api.UserApiBuilder
import com.example.anywherefitness.R
import com.example.anywherefitness.model.User
import com.example.anywherefitness.ui.LoginActivity
import kotlinx.android.synthetic.main.fragment_create_class_instructor.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateClassesFragment : Fragment() {

    private lateinit var createClassViewModel: CreateClassViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        createClassViewModel =
            ViewModelProviders.of(this).get(CreateClassViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_create_class_instructor, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var userId = 0
        val userObserver = Observer<User> {userId = it.id}
        createClassViewModel.getUser()?.observe(this, userObserver)

        btn_save_class.setOnClickListener{
            val newClass = createClassViewModel.createClass(
                et_class_name,
                et_class_type,
                et_class_time,
                et_class_duration,
                et_class_intensity,
                et_class_location,
                et_class_attendees,
                et_class_size)

            //TODO: put this in view model
            //TODO: figure out how to get class_Id back
            //TODO: get instructor_id programmatically
            println(userId)
            //will either need to update in the my classes fragment, or figure out something here
            val newCreateClass = CreateFitnessClass(newClass.name!!, newClass.type!!, newClass.location!!, newClass.duration!!, newClass.intensity!!,
                newClass.max_size.toString(), newClass.starttime, userId, null)

            val saveToken = activity?.getSharedPreferences(LoginActivity.SAVE_TOKEN, Context.MODE_PRIVATE)
            val getSavedToken = saveToken?.getString(LoginActivity.GET_SAVE_TOKEN, "default")

            UserApiBuilder.userRetro().addClass(getSavedToken!!, newCreateClass).enqueue(object: Callback<Void> {
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(context, "onFailure Failed to create class", Toast.LENGTH_LONG).show()
                    Log.i("BIGBRAIN", "onFailure $t")
                }

                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        //createClassViewModel.insert(newClass)
                        Toast.makeText(context, "new class created ${newClass.name}", Toast.LENGTH_LONG).show()
                        Log.i("BIGBRAIN", "success ${response.body()}")
                    } else {
                        Toast.makeText(context, "onResponse failed to create class", Toast.LENGTH_LONG).show()
                        Log.i("BIGBRAIN", "onFailure $response")
                    }
                }
            })
        }
    }
}