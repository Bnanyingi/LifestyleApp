package com.example.lifestyleapplication.ui.workoutplans.recommended

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentWorkOutPlanDaysBinding
import com.example.lifestyleapplication.ui.workoutplans.adapters.RecommendedDaysAdapter
import com.example.lifestyleapplication.ui.workoutplans.interfaces.RecommendedDaysInterface
import com.example.lifestyleapplication.ui.workoutplans.model.allplandaysmodel
import com.example.lifestyleapplication.ui.workoutplans.model.plandaysmodel
import com.example.lifestyleapplication.ui.workoutplans.retrofitclasses.RecommendedDaysRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WorkOutPlanDaysFragment : Fragment() {
    private lateinit var binding: FragmentWorkOutPlanDaysBinding
    private lateinit var recommendedDaysInterface: RecommendedDaysInterface
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var recommendedDaysAdapter: RecommendedDaysAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWorkOutPlanDaysBinding.inflate(inflater, container, false)
        binding.imgBackWork.setOnClickListener {
            findNavController().navigate(R.id.action_workOutPlanDaysFragment_to_workOutPlanCategories)
        }
        binding.progressWork.visibility = View.VISIBLE
        binding.recyclerWorkOutDays.visibility = View.GONE

        linearLayoutManager = LinearLayoutManager(activity)
        val activity = activity as Context
        recommendedDaysAdapter = RecommendedDaysAdapter(activity)
        getAllDays()
        return binding.root
    }

    private fun getAllDays() {
        recommendedDaysInterface = RecommendedDaysRetrofit.getRetrofit().create(RecommendedDaysInterface::class.java)
        val call: Call<allplandaysmodel> = recommendedDaysInterface.getDays("7")
        call.enqueue(object : Callback<allplandaysmodel>{
            override fun onResponse(
                call: Call<allplandaysmodel>,
                response: Response<allplandaysmodel>
            ) {
                if (response.isSuccessful) {
                    binding.progressWork.visibility = View.GONE
                    binding.recyclerWorkOutDays.visibility = View.VISIBLE
                    sendData(response.body()!!.data)
                }
            }

            override fun onFailure(call: Call<allplandaysmodel>, t: Throwable) {
                binding.progressWork.visibility = View.VISIBLE
                binding.recyclerWorkOutDays.visibility = View.GONE
                Toast.makeText(activity, "Error", Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun sendData(data: ArrayList<plandaysmodel>) {
        recommendedDaysAdapter.getDays(data)
        binding.recyclerWorkOutDays.adapter = recommendedDaysAdapter
        binding.recyclerWorkOutDays.layoutManager = linearLayoutManager
    }
}