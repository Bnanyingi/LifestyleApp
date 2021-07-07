package com.example.lifestyleapplication.ui.workoutplans.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.ui.interfaces.generalinterface
import com.example.lifestyleapplication.ui.workoutplans.model.workoutmodel

open class SelectedRecommendedWorkOutNumber(val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var wrkt: ArrayList<workoutmodel> = ArrayList()
    private lateinit var general: generalinterface
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.selected_workout_number_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val myViewHolder: MyViewHolder = holder as MyViewHolder
        myViewHolder.name.text = wrkt[position].exercise
        myViewHolder.card.setOnClickListener {
            general.sendExercise(wrkt[position])
        }
    }

    override fun getItemCount(): Int {
        return wrkt.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        general = context as generalinterface
    }

    protected class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        var name: TextView = view.findViewById(R.id.txtName)
        var card: CardView = view.findViewById(R.id.cardSelectedNumber)
    }

    fun getData(lst: ArrayList<workoutmodel>){
        for(i in lst){
            wrkt.add(i)
            notifyDataSetChanged()
        }
    }
}