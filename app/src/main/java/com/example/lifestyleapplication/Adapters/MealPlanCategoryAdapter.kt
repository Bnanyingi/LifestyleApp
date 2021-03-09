package com.example.lifestyleapplication.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lifestyleapplication.Interfaces.ProjectInterface
import com.example.lifestyleapplication.Model.MealCategory
import com.example.lifestyleapplication.R
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

open class MealPlanCategoryAdapter(val context: Context, var list: ArrayList<MealCategory>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var projectInterface: ProjectInterface
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.meal_plan_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val myViewHolder: MyViewHolder = holder as MyViewHolder
        myViewHolder.name.text = list[position].name
        myViewHolder.days.text = list[position].days
        myViewHolder.calories.text = list[position].calories
        val picasso: Picasso.Builder = Picasso.Builder(context)
        picasso.downloader(OkHttp3Downloader(context))
        picasso.build().load(list[position].img).into(myViewHolder.img)
        myViewHolder.linearLayout.setOnClickListener {
            projectInterface.sendCategory(list[position].name!!)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        if (context is ProjectInterface){
            projectInterface = context as ProjectInterface
        }
    }

    protected class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val name: TextView = view.findViewById(R.id.nameCat)
        val img: ImageView = view.findViewById(R.id.imgCat)
        val calories: TextView = view.findViewById(R.id.caloriesCat)
        val days: TextView = view.findViewById(R.id.daysCat)
        val linearLayout: LinearLayout = view.findViewById(R.id.linearCat)
    }
}