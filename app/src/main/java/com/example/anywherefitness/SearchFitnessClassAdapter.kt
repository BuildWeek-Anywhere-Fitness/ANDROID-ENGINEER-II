package com.example.anywherefitness

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.anywherefitness.Api.UserApiBuilder
import com.example.anywherefitness.database.UserRepo
import com.example.anywherefitness.model.FitnessClass
import com.example.anywherefitness.model.User
import kotlinx.android.synthetic.main.fitness_class_list_view.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchFitnessClassAdapter (val fitnessClassList: MutableList<FitnessClass>, val repo: UserRepo):
    RecyclerView.Adapter<SearchFitnessClassAdapter.ViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
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
        holder.fitnessClassDuration.text = fitnessClass.duration
        holder.fitnessClassIntensity.text = fitnessClass.intensity
        holder.fitnessClassLocation.text = fitnessClass.location
        holder.fitnessClassAttendees.text = fitnessClass.numberOfAttendees.toString()
        holder.fitnessClassMaxSize.text = fitnessClass.max_size.toString()
        holder.fitnessClassId.text = fitnessClass.id.toString()
        holder.fitnessClassParent.setOnClickListener {

            val builder = AlertDialog.Builder(context)
            builder.setTitle("Register Class")
            builder.setMessage("Are you sure you want to register for this class?")
            builder.setPositiveButton("YES"){dialog, which ->

                //TODO: make this work, currently cannot post in postman either, backend is working on it
                UserApiBuilder.userRetro().addUserToClass(fitnessClass.id, 12).enqueue(object: Callback<FitnessClass> {

                    override fun onFailure(call: Call<FitnessClass>, t: Throwable) {
                        Toast.makeText(context, "Error Registering for Class ${fitnessClass.name}", Toast.LENGTH_LONG).show()
                        Log.i("BIGBRAIN", "onFailure " + t.toString())
                    }

                    override fun onResponse(call: Call<FitnessClass>, response: Response<FitnessClass>) {
                        if (response.isSuccessful) {
                            repo.insert(fitnessClass)
                            fitnessClassList.removeAt(position)
                            notifyDataSetChanged()
                            Toast.makeText(context, "Registered for Class ${fitnessClass.name}", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(context, "Error Registering for Class ${fitnessClass.name}", Toast.LENGTH_LONG).show()
                            Log.i("BIGBRAIN", "Response not successful " + response)
                        }
                    }

                })


            }
            builder.setNegativeButton("NO"){_, _ ->}

            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
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