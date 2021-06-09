package com.example.lifestyleapplication.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.ui.constants.constants
import com.example.lifestyleapplication.ui.interfaces.generalinterface
import com.example.lifestyleapplication.ui.models.allDays
import com.example.lifestyleapplication.ui.models.day
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

open class DaysAdapter(val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var list: ArrayList<day> = ArrayList()
    private lateinit var general: generalinterface
    private var mealPLan: String? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.day_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val myViewHolder = holder as MyViewHolder
        myViewHolder.txtDay.text = list[position].day
        val picasso = Picasso.Builder(context)
        picasso.downloader(OkHttp3Downloader(context))
        picasso.build().load(constants.DEVOTIONALS + list[position].url).into(myViewHolder.img)
        myViewHolder.rel.setOnClickListener {
            general.goToDayMealPlans(list[position].day!!, mealPLan!!);
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
        val txtDay: TextView = view.findViewById(R.id.txtDay)
        val img: ImageView = view.findViewById(R.id.imgDay)
        val rel: RelativeLayout = view.findViewById(R.id.relDayClick)
    }

    fun getDays(lst: ArrayList<day>, plan: String){
        mealPLan = plan
        for (i in lst){
            list.add(i)
            notifyDataSetChanged()
        }
    }
}