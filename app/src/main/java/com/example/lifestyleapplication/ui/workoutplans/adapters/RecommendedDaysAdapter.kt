package com.example.lifestyleapplication.ui.workoutplans.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.ui.interfaces.generalinterface
import com.example.lifestyleapplication.ui.models.day
import com.example.lifestyleapplication.ui.workoutplans.model.plandaysmodel
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

open class RecommendedDaysAdapter(val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var days: ArrayList<plandaysmodel> = ArrayList()
    private lateinit var general: generalinterface
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.workout_days_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val myViewHolder: MyViewHolder = holder as MyViewHolder
        myViewHolder.dy.text = days[position].day
        val picasso: Picasso.Builder = Picasso.Builder(context)
        picasso.downloader(OkHttp3Downloader(context))
        picasso.build().load(days[position].image).into(myViewHolder.img)
        myViewHolder.card.setOnClickListener {
            general.sendDay(days[position].day.toString())
        }
    }

    override fun getItemCount(): Int {
        return days.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        general = context as generalinterface
    }

    protected class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        var card: CardView = view.findViewById(R.id.cardWorkOutDay)
        var dy: TextView = view.findViewById(R.id.workOutDay)
        var img: ImageView = view.findViewById(R.id.workOutImage)
    }

    fun getDays(lst: ArrayList<plandaysmodel>){
        for (i in lst){
            days.add(i)
            notifyDataSetChanged()
        }
    }

}