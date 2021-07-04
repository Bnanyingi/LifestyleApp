package com.example.lifestyleapplication.ui.specialdiet

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentSpecialDaysBinding
import com.example.lifestyleapplication.ui.adapters.DaysAdapter
import com.example.lifestyleapplication.ui.adapters.SpecialDaysAdapter
import com.example.lifestyleapplication.ui.interfaces.IntermittentDaysInterface
import com.example.lifestyleapplication.ui.interfaces.SpecialDaysInterface
import com.example.lifestyleapplication.ui.models.allDays
import com.example.lifestyleapplication.ui.models.day
import com.example.lifestyleapplication.ui.retrofit.IntermittentDaysRetrofit
import com.example.lifestyleapplication.ui.retrofit.SpecialDaysRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentSpecialDays : Fragment() {
    private lateinit var binding: FragmentSpecialDaysBinding
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var daysAdapter: SpecialDaysAdapter
    private lateinit var daysInterface: SpecialDaysInterface


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSpecialDaysBinding.inflate(inflater, container, false)
        binding.imgBackRec.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentSpecialDays_to_mealDurationFragment)
        }
        val activity = activity as Context
        linearLayoutManager = LinearLayoutManager(activity)
        daysAdapter = SpecialDaysAdapter(activity)
        binding.progressRec.visibility = View.VISIBLE
        binding.recyclerDays.visibility = View.GONE
        getData()
        return binding.root
    }

    private fun getData() {
        val brk: String = arguments?.getString("BREAKFAST").toString()
        val lnc: String = arguments?.getString("LUNCH").toString()
        val din: String = arguments?.getString("DINNER").toString()
        val dst: String = arguments?.getString("DESSERT").toString()
        val dy: String = arguments?.getString("DAY").toString()
        val pln: String = arguments?.getString("MEALPLAN").toString()
        val condition: String = arguments?.getString("CONDITION").toString()
        daysInterface = SpecialDaysRetrofit.getRetrofit().create(SpecialDaysInterface::class.java)
        val call: Call<allDays> = daysInterface.getDays()
        call.enqueue(object: Callback<allDays> {
            override fun onResponse(call: Call<allDays>, response: Response<allDays>) {
                if (response.isSuccessful){
                    binding.progressRec.visibility = View.GONE
                    binding.recyclerDays.visibility = View.VISIBLE
                    setData(response.body()!!.data, pln, brk, lnc, din, dst, condition, dy)
                }
            }

            override fun onFailure(call: Call<allDays>, t: Throwable) {
                Toast.makeText(activity, t.message.toString(), Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setData(data: ArrayList<day>, plan: String, brk: String, lnc: String, din: String, dst: String, condition: String, dy: String) {
        val lst: ArrayList<day> = ArrayList()
        if (data.size >= dy.toInt()){
            for (i in 0 until dy.toInt()){
                val d = day()
                d.id = data[i].id
                d.day = data[i].day
                d.url = data[i].url
                lst.add(d)
            }
            daysAdapter.getDays(lst, plan, brk, lnc, din, dst, condition)
            binding.recyclerDays.layoutManager = linearLayoutManager
            binding.recyclerDays.adapter = daysAdapter

        }
    }
}