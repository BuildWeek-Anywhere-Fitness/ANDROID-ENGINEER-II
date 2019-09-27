package com.example.anywherefitness.ui.client

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.anywherefitness.R
import com.example.anywherefitness.database.UserRepo
import com.example.anywherefitness.model.FitnessClass
import com.example.anywherefitness.ui.LoginActivity
import kotlinx.android.synthetic.main.fitness_class_list_view.view.*
import kotlinx.android.synthetic.main.fragment_find_classes.*

class FindClassesFragment : Fragment() {

    private lateinit var findClassesViewModel: FindClassesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        findClassesViewModel =
            ViewModelProviders.of(this).get(FindClassesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_find_classes, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val saveToken = activity?.getSharedPreferences(LoginActivity.SAVE_TOKEN, Context.MODE_PRIVATE)
        val getSavedToken = saveToken?.getString(LoginActivity.GET_SAVE_TOKEN, "default")
        findClassesViewModel.getList(getSavedToken!!)


        val list = mutableListOf<FitnessClass>()




        rv_find_classes.layoutManager = LinearLayoutManager(context)
        rv_find_classes.adapter = SearchFitnessClassAdapter(list)

        val something = Observer<List<FitnessClass>> {
            list.addAll(it)
            list.add(it[it.size - 1])
            rv_find_classes.adapter?.notifyDataSetChanged()
        }
        
        findClassesViewModel.resultList.observe(this, something)

        //something.onChanged(findClassesViewModel.resultList.value)

        sv_find.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            val newList = mutableListOf<FitnessClass>()
            override fun onQueryTextSubmit(p0: String?): Boolean {
                list.forEach {
                    when (p0){
                        it.name -> newList.add(it)
                        it.duration -> newList.add(it)
                        it.type -> newList.add(it)
                        it.intensity -> newList.add(it)
                        it.location -> newList.add(it)
                    }
                }
                list.clear()
                list.addAll(newList)
                rv_find_classes.adapter?.notifyDataSetChanged()
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                list.clear()
                newList.clear()
                something.onChanged(findClassesViewModel.resultList.value)
                if (p0.isNullOrBlank()){
                    rv_find_classes.adapter?.notifyDataSetChanged()
                }


                return true
            }

        })
        



    }

    inner class SearchFitnessClassAdapter (val fitnessClassList: MutableList<FitnessClass>):
        RecyclerView.Adapter<SearchFitnessClassAdapter.ViewHolder>() {

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
                    UserRepo(context!!).insert(fitnessClass)
                    fitnessClassList.removeAt(position)
                    notifyDataSetChanged()
                    Toast.makeText(context, "Registered for Class ${fitnessClass.name}", Toast.LENGTH_LONG).show()
                }
                builder.setNegativeButton("NO"){_, _ ->}

                val dialog: AlertDialog = builder.create()
                dialog.show()
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