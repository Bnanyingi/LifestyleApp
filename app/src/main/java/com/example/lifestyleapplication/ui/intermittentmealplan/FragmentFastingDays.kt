package com.example.lifestyleapplication.ui.intermittentmealplan

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
import com.example.lifestyleapplication.databinding.FragmentFastingDaysBinding
import com.example.lifestyleapplication.ui.adapters.DaysAdapter
import com.example.lifestyleapplication.ui.adapters.FastingDaysAdapter
import com.example.lifestyleapplication.ui.interfaces.DaysInterface
import com.example.lifestyleapplication.ui.interfaces.IntermittentDaysInterface
import com.example.lifestyleapplication.ui.models.allDays
import com.example.lifestyleapplication.ui.models.day
import com.example.lifestyleapplication.ui.retrofit.DaysRetrofit
import com.example.lifestyleapplication.ui.retrofit.IntermittentDaysRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentFastingDays : Fragment() {
    private lateinit var binding: FragmentFastingDaysBinding
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var daysAdapter: FastingDaysAdapter
    private lateinit var daysInterface: IntermittentDaysInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFastingDaysBinding.inflate(inflater, container, false)
        binding.imgBackRec.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentFastingDays_to_intermittentMealPlanFragment)
        }
        val activity = activity as Context
        linearLayoutManager = LinearLayoutManager(activity)
        daysAdapter = FastingDaysAdapter(activity)
        binding.progressRec.visibility = View.VISIBLE
        binding.recyclerDays.visibility = View.GONE
        getData()
        return binding.root
    }

    private fun getData() {
        daysInterface = IntermittentDaysRetrofit.getRetrofit().create(IntermittentDaysInterface::class.java)
        val call: Call<allDays> = daysInterface.getDays()
        call.enqueue(object: Callback<allDays> {
            override fun onResponse(call: Call<allDays>, response: Response<allDays>) {
                if (response.isSuccessful){
                    binding.progressRec.visibility = View.GONE
                    binding.recyclerDays.visibility = View.VISIBLE
                    val dys = arguments?.getString("DAYS").toString()
                    setData(response.body()!!.data, arguments?.getString("MEALPLAN").toString(), dys)
                }
            }

            override fun onFailure(call: Call<allDays>, t: Throwable) {
                Toast.makeText(activity, t.message.toString(), Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setData(data: ArrayList<day>, plan: String, dys: String) {
        val lst: ArrayList<day> = ArrayList()
        if (data.size >= dys.toInt()){
            for (i in 0 until dys.toInt()){
                val d = day()
                d.id = data[i].id
                d.day = data[i].day
                d.url = data[i].url
                lst.add(d)
            }
            daysAdapter.getDays(lst, plan)
            binding.recyclerDays.layoutManager = linearLayoutManager
            binding.recyclerDays.adapter = daysAdapter

        }

    }

}