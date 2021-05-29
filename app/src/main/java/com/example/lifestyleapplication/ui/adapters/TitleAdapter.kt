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
import com.example.lifestyleapplication.ui.models.titles

open class TitleAdapter(val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var list: ArrayList<titles> = ArrayList()
    private var auth: String? = ""
    private lateinit var general: generalinterface

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.title_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val myViewHolder: MyViewHolder = holder as MyViewHolder
        myViewHolder.title.text = list[position].title
        myViewHolder.card.setOnClickListener {
            general.sendTitle(list[position].title, auth!!)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        general = context as generalinterface
    }

    protected class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val title: TextView = view.findViewById(R.id.title)
        val card: CardView = view.findViewById(R.id.cardTitle)
    }

    fun getData(newList: List<titles>, author: String){
        for (i in newList){
            list.add(i)
            notifyDataSetChanged()
        }
        notifyDataSetChanged()
        auth = author
    }
}