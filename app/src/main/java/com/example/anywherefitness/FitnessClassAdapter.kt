package com.example.anywherefitness

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.anywherefitness.model.FitnessClass
import kotlinx.android.synthetic.main.fitness_class_list_view.view.*

class FitnessClassAdapter (val fitnessClassList: MutableList<FitnessClass>,
                           clickListener: View.OnClickListener,
                           longClickListener: View.OnLongClickListener?): RecyclerView.Adapter<FitnessClassAdapter.ViewHolder>() {

    private val clickListener = clickListener
    private val longClickListener = longClickListener

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
        holder.fitnessClassTime.text = fitnessClass.startTime
        holder.fitnessClassDuration.text = fitnessClass.duration.toString()
        holder.fitnessClassIntensity.text = fitnessClass.intensity.toString()
        holder.fitnessClassLocation.text = fitnessClass.location
        holder.fitnessClassAttendees.text = fitnessClass.numberOfAttendees.toString()
        holder.fitnessClassMaxSize.text = fitnessClass.classSize.toString()
        holder.fitnessClassParent.setOnClickListener(clickListener)
        holder.fitnessClassParent.setOnLongClickListener(longClickListener)

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
    }
}