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
import com.example.lifestyleapplication.ui.models.devotionalTopicTitles

open class SelectedDevotionTopicAdapter(val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var list: ArrayList<devotionalTopicTitles> = ArrayList()
    private lateinit var general: generalinterface
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.selected_devotion_topic_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var myViewHolder: MyViewHolder = holder as MyViewHolder
        myViewHolder.author.text = list[position].author
        myViewHolder.topic.text = list[position].title
        myViewHolder.card.setOnClickListener { 
            general.getSelectedDevotionalTitle(list[position].title!!)
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
        val topic: TextView = view.findViewById(R.id.devTopicSelected)
        val author: TextView = view.findViewById(R.id.devAuthorSelected)
        val card: CardView = view.findViewById(R.id.cardTopicSelected)
    }

    fun addAll(lst: List<devotionalTopicTitles>){
        for (i in lst){
            list.add(i)
            notifyDataSetChanged()
        }
    }
}