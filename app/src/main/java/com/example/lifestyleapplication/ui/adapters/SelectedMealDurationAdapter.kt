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
import com.example.lifestyleapplication.ui.models.selectedday
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

open class SelectedMealDurationAdapter(val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var list: ArrayList<selectedday> = ArrayList()
    private lateinit var general: generalinterface
    private var mealPlan: String? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.selected__meal_duration_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val myViewHolder = holder as MyViewHolder
        myViewHolder.title.text = list[position].meal
        val picasso = Picasso.Builder(context)
        picasso.downloader(OkHttp3Downloader(context))
        picasso.build().load(constants.DEVOTIONALS + list[position].mealimage).into(myViewHolder.img)
        myViewHolder.rel.setOnClickListener {
            general.getPlanDetails(list[position], mealPlan!!)
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
        val title: TextView = view.findViewById(R.id.txtDuration)
        val img: ImageView = view.findViewById(R.id.imgDuration)
        val rel: RelativeLayout = view.findViewById(R.id.relDuration)
    }

    fun getDuration(lst: ArrayList<selectedday>, plan: String){
        mealPlan = plan
        for (i in lst){
            list.add(i)
            notifyDataSetChanged()
        }
    }
}