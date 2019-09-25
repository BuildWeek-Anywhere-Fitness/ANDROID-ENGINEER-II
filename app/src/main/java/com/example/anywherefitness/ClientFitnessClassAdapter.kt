package com.example.anywherefitness

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.anywherefitness.database.UserRepo
import com.example.anywherefitness.model.FitnessClass
import kotlinx.android.synthetic.main.fitness_class_list_view.view.*


class ClientFitnessClassAdapter (val fitnessClassList: MutableList<FitnessClass>, val repo: UserRepo): RecyclerView.Adapter<ClientFitnessClassAdapter.ViewHolder>() {

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
        holder.fitnessClassTime.text = fitnessClass.startTime
        holder.fitnessClassDuration.text = fitnessClass.duration.toString()
        holder.fitnessClassIntensity.text = fitnessClass.intensity.toString()
        holder.fitnessClassLocation.text = fitnessClass.location
        holder.fitnessClassAttendees.text = fitnessClass.numberOfAttendees.toString()
        holder.fitnessClassMaxSize.text = fitnessClass.classSize.toString()
        holder.fitnessClassId.text = fitnessClass.id.toString()
        holder.fitnessClassParent.setOnLongClickListener {

            val builder = AlertDialog.Builder(context)
            builder.setTitle("Unregister Class")
            builder.setMessage("Are you sure you want to unregister from this class?")
            builder.setPositiveButton("YES"){dialog, which ->
                repo.deleteClassById(fitnessClass.id)
                fitnessClassList.removeAt(position)
                notifyDataSetChanged()
            }
            builder.setNegativeButton("NO"){_, _ ->}

            val dialog: AlertDialog = builder.create()
            dialog.show()
            true
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