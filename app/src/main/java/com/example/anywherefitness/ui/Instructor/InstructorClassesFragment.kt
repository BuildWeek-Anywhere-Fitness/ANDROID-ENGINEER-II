package com.example.anywherefitness.ui.Instructor

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.anywherefitness.Api.UserApiBuilder
import com.example.anywherefitness.R
import com.example.anywherefitness.database.UserRepo
import com.example.anywherefitness.model.FitnessClass
import com.example.anywherefitness.model.User
import com.example.anywherefitness.ui.LoginActivity
import kotlinx.android.synthetic.main.fitness_class_list_view.view.*
import kotlinx.android.synthetic.main.fragment_classes_instructor.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InstructorClassesFragment : Fragment() {

    private lateinit var instructorClassesViewModel: InstructorClassesViewModel
    private lateinit var fitnessClassList : MutableList<FitnessClass>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        instructorClassesViewModel =
            ViewModelProviders.of(this).get(InstructorClassesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_classes_instructor, container, false)
        val textView: TextView = root.findViewById(R.id.text_classes_instructor)
        instructorClassesViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val saveToken = activity?.getSharedPreferences(LoginActivity.SAVE_TOKEN, Context.MODE_PRIVATE)
        val getSavedToken = saveToken?.getString(LoginActivity.GET_SAVE_TOKEN, "default")

        var userId = 0
        val userObserver = Observer<User> {userId = it.id}
        instructorClassesViewModel.getUser()?.observe(this, userObserver)

        //TODO: put this in view model, remove magic number for instructor id
        //calling all classes here and then filtering for the correct instructor_id because when a class is posted it does not
        //return the class_id, so we can't save it in the database correctly
        UserApiBuilder.userRetro().getAllClasses(getSavedToken!!).enqueue(object: Callback<List<FitnessClass>> {
            override fun onFailure(call: Call<List<FitnessClass>>, t: Throwable) {
                Log.i("BIGBRAIN", "onFailure $t")
            }

            override fun onResponse(call: Call<List<FitnessClass>>, response: Response<List<FitnessClass>>) {
                if (response.isSuccessful) {
                    Log.i("BIGBRAIN", "onReponse ${response.body()?.size}")
                    instructorClassesViewModel.deleteAllClasses()
                    fitnessClassList = response.body()!!.toMutableList()
                    for (i in 0 until fitnessClassList.size) {
                        if (fitnessClassList[i].instructor_id == userId) {
                            instructorClassesViewModel.insert(fitnessClassList[i])
                        }
                    }
                    fitnessClassList = instructorClassesViewModel.getAllClasses()
                    rv_instructor_classes.adapter?.notifyDataSetChanged()
                } else {
                    Log.i("BIGBRAIN", "onReponse failure $response")
                }
            }

        })

        fitnessClassList = instructorClassesViewModel.getAllClasses()
        rv_instructor_classes.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(context)
            adapter = FitnessClassAdapter(fitnessClassList)
        }
    }

    inner class FitnessClassAdapter (val fitnessClassList: MutableList<FitnessClass>): RecyclerView.Adapter<FitnessClassAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fitness_class_list_view, parent, false) as View)
        }

        override fun getItemCount(): Int {
            return fitnessClassList.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val fitnessClass = fitnessClassList[position]
            holder.fitnessClassName.text = fitnessClass.name
            holder.fitnessClassType.text = fitnessClass.type
            holder.fitnessClassTime.text = fitnessClass.starttime
            holder.fitnessClassDuration.text = fitnessClass.duration.toString()
            holder.fitnessClassIntensity.text = fitnessClass.intensity.toString()
            holder.fitnessClassLocation.text = fitnessClass.location
            holder.fitnessClassAttendees.text = fitnessClass.numberOfAttendees.toString()
            holder.fitnessClassMaxSize.text = fitnessClass.max_size.toString()
            holder.fitnessClassId.text = fitnessClass.id.toString()
            holder.fitnessClassParent.setOnLongClickListener {


                val saveToken = activity?.getSharedPreferences(LoginActivity.SAVE_TOKEN, Context.MODE_PRIVATE)
                val getSavedToken = saveToken?.getString(LoginActivity.GET_SAVE_TOKEN, "default")

                val builder = AlertDialog.Builder(context)
                builder.setTitle("Delete Class")
                builder.setMessage("Are you sure you want to delete this class?")
                builder.setPositiveButton("YES"){dialog, which ->
                    UserApiBuilder.userRetro().deleteClass(getSavedToken!!, fitnessClass.id).enqueue(object: Callback<Void> {
                        override fun onFailure(call: Call<Void>, t: Throwable) {
                            Log.i("BIGBRAIN", "onFailure $t")
                        }

                        override fun onResponse(call: Call<Void>, response: Response<Void>) {
                            if (response.isSuccessful) {
                                instructorClassesViewModel.repo.deleteClassById(fitnessClass.id)
                                fitnessClassList.removeAt(position)
                                notifyDataSetChanged()
                                Log.i("BIGBRAIN", "reponse success $response")
                            } else {
                                Log.i("BIGBRAIN", "reponse failed $response")
                            }
                        }

                    })
                }
                builder.setNegativeButton("NO"){_, _ ->}

                val dialog: AlertDialog = builder.create()
                dialog.show()
                true
            }

        }


        inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
            val fitnessClassName: TextView = view.tv_class_name
            val fitnessClassType: TextView = view.tv_class_type
            val fitnessClassTime: TextView = view.tv_class_time
            val fitnessClassDuration: TextView = view.tv_class_duration
            val fitnessClassIntensity: TextView = view.tv_class_intensity
            val fitnessClassLocation: TextView = view.tv_class_location
            val fitnessClassAttendees: TextView = view.tv_class_registered_attendees
            val fitnessClassMaxSize: TextView = view.tv_class_max_size
            val fitnessClassParent: CardView = view.cv_parent
            val fitnessClassId: TextView = view.tv_class_id
        }
    }
}