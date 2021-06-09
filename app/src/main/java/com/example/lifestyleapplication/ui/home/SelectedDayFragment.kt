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
import com.example.lifestyleapplication.databinding.FragmentSelectedDayBinding
import com.example.lifestyleapplication.ui.adapters.SelectedMealDurationAdapter
import com.example.lifestyleapplication.ui.interfaces.DayMealDurationInterface
import com.example.lifestyleapplication.ui.models.AllSelectedDays
import com.example.lifestyleapplication.ui.models.selectedday
import com.example.lifestyleapplication.ui.retrofit.SelectedDayRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SelectedDayFragment : Fragment() {
    private lateinit var binding: FragmentSelectedDayBinding
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var selectedMealDurationAdapter: SelectedMealDurationAdapter
    private lateinit var dayMealDurationInterface: DayMealDurationInterface


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSelectedDayBinding.inflate(inflater, container, false)
        binding.selectedBack.setOnClickListener {
            findNavController().navigate(R.id.action_selectedDayFragment_to_recommendedMealPlanFragment)
        }
        binding.progressSelected.visibility = View.VISIBLE
        binding.recyclerSelectedDay.visibility = View.GONE
        binding.day.text = arguments?.getString("DAY")
        val activity = activity as Context
        linearLayoutManager = LinearLayoutManager(activity)
        selectedMealDurationAdapter = SelectedMealDurationAdapter(activity)
        getSelectedDuration()
        return binding.root
    }

    private fun getSelectedDuration() {
        val day: String = arguments?.getString("DAY").toString()
        dayMealDurationInterface = SelectedDayRetrofit.getRetrofit().create(DayMealDurationInterface::class.java)
        val call: Call<AllSelectedDays> = dayMealDurationInterface.getDuration(day)
        call.enqueue(object: Callback<AllSelectedDays> {
            override fun onResponse(
                call: Call<AllSelectedDays>,
                response: Response<AllSelectedDays>
            ) {
                if (response.isSuccessful){
                    binding.progressSelected.visibility = View.GONE
                    binding.recyclerSelectedDay.visibility = View.VISIBLE
                    showDurations(response.body()!!.data, arguments?.getString("PLAN").toString())
                }
            }

            override fun onFailure(call: Call<AllSelectedDays>, t: Throwable) {
                Toast.makeText(activity, t.message.toString(), Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun showDurations(data: ArrayList<selectedday>, plan: String) {
        selectedMealDurationAdapter.getDuration(data, plan)
        binding.recyclerSelectedDay.adapter = selectedMealDurationAdapter
        binding.recyclerSelectedDay.layoutManager = linearLayoutManager
    }
}