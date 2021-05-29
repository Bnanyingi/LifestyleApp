package com.example.lifestyleapplication.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.ui.interfaces.generalinterface
import com.example.lifestyleapplication.ui.models.devotionalTopics
import com.example.lifestyleapplication.ui.models.devotions
import java.util.zip.Inflater

open class DevotionalsAdapter(val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var list: ArrayList<devotionalTopics> = ArrayList()
    private lateinit var general: generalinterface

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.devotion_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val myViewHolder: MyViewHolder = holder as MyViewHolder
        myViewHolder.title.text = list[position].topic
        myViewHolder.card.setOnClickListener {
            general.getDevotionTopics(list[position].topic!!)
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        general = context as generalinterface
    }

    override fun getItemCount(): Int {
        return list.size
    }

    protected class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val title: TextView = view.findViewById(R.id.titleDevotion)
        val card: CardView = view.findViewById(R.id.cardDevotion)
    }

    fun addAll(lst: List<devotionalTopics>){
        for (i in lst){
            list.add(i)
            notifyDataSetChanged()
        }
    }
}