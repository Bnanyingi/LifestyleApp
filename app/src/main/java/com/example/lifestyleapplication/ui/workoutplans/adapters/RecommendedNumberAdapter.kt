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
import com.example.lifestyleapplication.ui.workoutplans.model.workoutnumbermodel
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

open class RecommendedNumberAdapter(val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var numb: ArrayList<workoutnumbermodel> = ArrayList()
    private lateinit var general: generalinterface
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.workout_number_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var myViewHolder: MyViewHolder = holder as MyViewHolder
        myViewHolder.title.text = numb[position].workoutnumber + ": " + numb[position].workoutbodypart
        var picasso: Picasso.Builder = Picasso.Builder(context)
        picasso.downloader(OkHttp3Downloader(context))
        picasso.build().load(numb[position].img).into(myViewHolder.img)
        myViewHolder.card.setOnClickListener {
            general.sendWorkOutNumber(numb[position].workoutnumber!!, numb[position].img!!)
        }
    }

    override fun getItemCount(): Int {
        return numb.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        general = context as generalinterface
    }

    protected class MyViewHolder(view : View): RecyclerView.ViewHolder(view){
        var card: CardView = view.findViewById(R.id.cardNumber)
        var title: TextView = view.findViewById(R.id.txtNumberName)
        var img: ImageView = view.findViewById(R.id.imgNumber)
    }

    fun getData(lst: ArrayList<workoutnumbermodel>){
        for (i in lst){
            numb.add(i)
            notifyDataSetChanged()
        }
    }
}