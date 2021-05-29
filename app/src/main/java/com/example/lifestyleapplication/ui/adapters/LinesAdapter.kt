package com.example.lifestyleapplication.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lifestyleapplication.R
import okhttp3.internal.ignoreIoExceptions
import java.util.zip.Inflater

open class LinesAdapter(val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val list: ArrayList<String> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.lines_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val myViewHolder: MyViewHolder = holder as MyViewHolder
        myViewHolder.textView.text = list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }

    protected class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val textView: TextView = view.findViewById(R.id.poemLines)
    }

    fun addAll(lines: List<String>){
        for (i in lines){
            list.add(i)
            notifyDataSetChanged()
        }
        notifyDataSetChanged()
    }
}