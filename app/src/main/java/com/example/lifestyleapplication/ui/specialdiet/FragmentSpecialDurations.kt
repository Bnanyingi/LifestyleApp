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
import com.example.lifestyleapplication.databinding.FragmentSpecialDurationsBinding
import com.example.lifestyleapplication.ui.adapters.SelectedMealDurationAdapter
import com.example.lifestyleapplication.ui.adapters.SpecialMealDurationAdapter
import com.example.lifestyleapplication.ui.interfaces.IntermittentDurationInterface
import com.example.lifestyleapplication.ui.interfaces.SpecialDurationInterface
import com.example.lifestyleapplication.ui.models.AllSelectedDays
import com.example.lifestyleapplication.ui.models.selectedday
import com.example.lifestyleapplication.ui.retrofit.IntermittentDurationRetrofit
import com.example.lifestyleapplication.ui.retrofit.SpecialDurationRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentSpecialDurations : Fragment() {
    private lateinit var binding: FragmentSpecialDurationsBinding
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var selectedMealDurationAdapter: SpecialMealDurationAdapter
    private lateinit var dayMealDurationInterface: SpecialDurationInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSpecialDurationsBinding.inflate(inflater, container, false)
        binding.selectedBack.setOnClickListener {
            findNavController().navigate(R.id.action_selectedDayFragment_to_recommendedMealPlanFragment)
        }
        binding.progressSelected.visibility = View.VISIBLE
        binding.recyclerSelectedDay.visibility = View.GONE
        binding.day.text = arguments?.getString("DAY")
        val activity = activity as Context
        linearLayoutManager = LinearLayoutManager(activity)
        selectedMealDurationAdapter = SpecialMealDurationAdapter(activity)

        getSelectedDuration()
        return binding.root
    }

    private fun getSelectedDuration() {
        val pln: String = arguments?.getString("MEALPLAN").toString()
        val condition: String = arguments?.getString("CONDITION").toString()
        val day: String = arguments?.getString("DAY").toString()
        dayMealDurationInterface = SpecialDurationRetrofit.getRetrofit().create(SpecialDurationInterface::class.java)
        val call: Call<AllSelectedDays> = dayMealDurationInterface.getDuration(day)
        call.enqueue(object: Callback<AllSelectedDays> {
            override fun onResponse(
                call: Call<AllSelectedDays>,
                response: Response<AllSelectedDays>
            ) {
                if (response.isSuccessful){
                    binding.progressSelected.visibility = View.GONE
                    binding.recyclerSelectedDay.visibility = View.VISIBLE
                    showDurations(response.body()!!.data, arguments?.getString("PLAN").toString(), condition)
                }
            }

            override fun onFailure(call: Call<AllSelectedDays>, t: Throwable) {
                Toast.makeText(activity, t.message.toString(), Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun showDurations(data: ArrayList<selectedday>, plan: String, condition: String) {
        val brk: String = arguments?.getString("BREAKFAST").toString()
        val lnc: String = arguments?.getString("LUNCH").toString()
        val din: String = arguments?.getString("DINNER").toString()
        val dst: String = arguments?.getString("DESSERT").toString()
        val mls: ArrayList<String> = ArrayList()
        mls[0] = brk
        mls[1] = lnc
        mls[2] = din
        mls[3] = dst

        val lst: ArrayList<selectedday> = ArrayList()

        for (i in data.indices){
            for (j in mls.indices){
                if (data[i].meal == mls[j]){
                    lst.add(data[i])
                }
            }
        }
        selectedMealDurationAdapter.getDuration(lst, plan, condition)
        binding.recyclerSelectedDay.adapter = selectedMealDurationAdapter
        binding.recyclerSelectedDay.layoutManager = linearLayoutManager
    }

}