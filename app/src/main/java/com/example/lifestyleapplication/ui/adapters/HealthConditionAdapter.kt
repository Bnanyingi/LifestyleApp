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
import com.example.lifestyleapplication.ui.models.HealthConditions

open class HealthConditionAdapter(val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {
    private val lst: ArrayList<HealthConditions> = ArrayList()
    private lateinit var filteredLst: ArrayList<HealthConditions>
    private lateinit var general: generalinterface
    private var pln: String? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.illness_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val myViewHolder = holder as MyViewHolder
        myViewHolder.condition.text = lst[position].condition
        myViewHolder.rel.setOnClickListener {
            pln?.let { it1 -> general.sendCondition(lst[position].condition.toString(), it1) }
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        general = context as generalinterface
    }

    override fun getItemCount(): Int {
        return lst.size
    }

    protected class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val condition: TextView = view.findViewById(R.id.illnessTitle)
        val rel: RelativeLayout = view.findViewById(R.id.relIllness)
    }



    fun addAll(lstConditions: ArrayList<HealthConditions>, plan: String){
        pln = plan
        for (i in lstConditions){
            lst.add(i)
            notifyDataSetChanged()
        }
        filteredLst = ArrayList(lstConditions)
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(p0: CharSequence?): FilterResults {
                var lstNew: ArrayList<HealthConditions> = ArrayList()
                if (p0.isNullOrEmpty()){
                    lstNew.addAll(filteredLst)
                }
                else{
                    var str = p0.toString().toLowerCase().trim()
                    for (j: HealthConditions in filteredLst){
                        if (j.condition!!.toLowerCase().contains(str)){
                            lstNew.add(j)
                        }
                    }
                }

                val filterResults = FilterResults()
                filterResults.values = lstNew
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                lst.clear()
                lst.addAll(p1!!.values as ArrayList<HealthConditions>)
                notifyDataSetChanged()
            }

        }
    }
}