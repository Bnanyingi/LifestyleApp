package com.example.lifestyleapplication.ui.home

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
import com.example.lifestyleapplication.databinding.FragmentRecommendedMealPlanBinding
import com.example.lifestyleapplication.ui.adapters.DaysAdapter
import com.example.lifestyleapplication.ui.adapters.SelectedMealDurationAdapter
import com.example.lifestyleapplication.ui.interfaces.DayMealDurationInterface
import com.example.lifestyleapplication.ui.interfaces.DaysInterface
import com.example.lifestyleapplication.ui.models.AllSelectedDays
import com.example.lifestyleapplication.ui.models.allDays
import com.example.lifestyleapplication.ui.models.day
import com.example.lifestyleapplication.ui.models.selectedday
import com.example.lifestyleapplication.ui.retrofit.DaysRetrofit
import com.example.lifestyleapplication.ui.retrofit.SelectedDayRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecommendedMealPlanFragment : Fragment() {
    private lateinit var binding: FragmentRecommendedMealPlanBinding
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var daysAdapter: DaysAdapter
    private lateinit var daysInterface: DaysInterface


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecommendedMealPlanBinding.inflate(inflater, container, false)
        binding.imgBackRec.setOnClickListener {
            findNavController().navigate(R.id.action_recommendedMealPlanFragment_to_mealPlanCategoriesFragment)
        }
        val activity = activity as Context
        linearLayoutManager = LinearLayoutManager(activity)
        daysAdapter = DaysAdapter(activity)
        binding.progressRec.visibility = View.VISIBLE
        binding.recyclerDays.visibility = View.GONE
        getData()
        return binding.root
    }

    private fun getData() {
        daysInterface = DaysRetrofit.getRetrofit().create(DaysInterface::class.java)
        val call: Call<allDays> = daysInterface.getDays()
        call.enqueue(object: Callback<allDays>{
            override fun onResponse(call: Call<allDays>, response: Response<allDays>) {
                if (response.isSuccessful){
                    binding.progressRec.visibility = View.GONE
                    binding.recyclerDays.visibility = View.VISIBLE
                    setData(response.body()!!.data)
                }
            }

            override fun onFailure(call: Call<allDays>, t: Throwable) {
                Toast.makeText(activity, t.message.toString(), Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun setData(data: ArrayList<day>) {
        daysAdapter.getDays(data)
        binding.recyclerDays.layoutManager = linearLayoutManager
        binding.recyclerDays.adapter = daysAdapter
    }

}