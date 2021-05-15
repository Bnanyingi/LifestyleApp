package com.example.lifestyleapplication.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.ui.interfaces.generalinterface

open class authorAdapter(val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private var authors: ArrayList<String> = ArrayList()
    private lateinit var general: generalinterface

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.author_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val myViewHolder: MyViewHolder = holder as MyViewHolder
        myViewHolder.txt.text = authors[position]
        myViewHolder.rel.setOnClickListener {
            general.sendAuthor(authors[position])
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        general = context as generalinterface
    }

    override fun getItemCount(): Int {
        return authors.size
    }

    protected class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var txt: TextView = view.findViewById(R.id.authorName)
        var rel: RelativeLayout = view.findViewById(R.id.authorRel)
    }

    fun getData(list: ArrayList<String>){
        for (i in list){
            authors.add(i)
            notifyDataSetChanged()
        }
        notifyDataSetChanged()
    }
}