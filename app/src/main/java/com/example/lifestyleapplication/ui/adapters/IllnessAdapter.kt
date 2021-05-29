package com.example.lifestyleapplication.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.ui.interfaces.generalinterface
import com.example.lifestyleapplication.ui.models.illness
import java.util.Locale.filter

open class IllnessAdapter(val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {
    private var list: ArrayList<illness> = ArrayList()
    private lateinit var filterList: ArrayList<illness>
    private lateinit var general: generalinterface
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.illness_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var myViewHolder: MyViewHolder = holder as MyViewHolder
        myViewHolder.name.text = list[position].name
        myViewHolder.rel.setOnClickListener {
            general.getIllness(list[position].name!!)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        general = context as generalinterface
    }

    protected class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        var name: TextView = view.findViewById(R.id.illnessTitle)
        var rel: RelativeLayout = view.findViewById(R.id.relIllness)
    }

    override fun getFilter(): Filter {
        return object: Filter(){
            override fun performFiltering(p0: CharSequence?): FilterResults {
                var filteredList: ArrayList<illness> = ArrayList()
                if (p0.isNullOrBlank()){
                    filteredList.addAll(filterList)
                }
                else{
                    var string = p0.toString().toLowerCase().trim()
                    for (i: illness in filterList){
                        if (i.name.toString().toLowerCase().contains(string)){
                            filteredList.add(i)
                        }
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                list.clear()
                list.addAll(p1!!.values as ArrayList<illness>)
                notifyDataSetChanged()
            }

        }
    }

    fun addAll(lst: ArrayList<illness>){
        for (i in lst){
            list.add(i)
            notifyDataSetChanged()
        }
        filterList = ArrayList(lst)
        notifyDataSetChanged()
    }
}